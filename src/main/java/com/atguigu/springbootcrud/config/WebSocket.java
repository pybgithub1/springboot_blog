package com.atguigu.springbootcrud.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springbootcrud.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@Controller
@ServerEndpoint("/websocket/{username}")
public class WebSocket {

    /* 在线人数 */
    public static int onlineNumber = 0;
    /* 以用户的姓名为key，WebSocket为对象保存起来*/
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /* 会话 */
    private Session session;
    /* 用户名称 */
    private String username;

    private static MsgService msgService;

    @Autowired
    public void setChatService(MsgService msgService) {
        this.msgService = msgService;
    }
    /* OnOpen 表示有浏览器链接过来的时候被调用
     * OnClose 表示浏览器发出关闭请求的时候被调用
     * OnMessage 表示浏览器发消息的时候被调用
     * OnError 表示有错误发生，比如网络断开了等等 */

    /* 建立连接 */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        log.info("现在来连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
        log.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 1);
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);
            //把自己的信息websocket加入到clients当中去
            clients.put(username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 = new HashMap<>();
            map2.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers", set);
            //下面给所有人发送消息时候获取除了发送者本身之外的其他人的name
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (IOException e) {
            log.info(username + "上线的时候通知所有人发生了错误");
        }
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                //意思就是把一个用户13656641499发送给后端的消息传送到另一个用户pyb的前端页面进行控制台打印信息以及显示消息
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    /* 收到客户端的消息 */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromuser = jsonObject.getString("username");
            String touser = jsonObject.getString("to");
            Date date = new Date();//获取当前的日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(date);//获取String类型的时间
            Boolean msg1 = msgService.insertMsgByFromuserAndTouserAndMsg(fromuser, touser, textMessage, time);
            System.out.println(fromuser + "对" + touser + "说的消息存储成功");
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 4);
            map1.put("textMessage", textMessage);
            map1.put("fromuser", fromuser);
            map1.put("touser", touser);
            sendMessageTo(JSON.toJSONString(map1), touser);
        } catch (Exception e) {
            log.info("发生了错误了");
        }
    }


    /*
    发生错误
    */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
        //error.printStackTrace();
    }

    /*
     连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(username);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 2);
            map1.put("onlineUsers", clients.keySet());
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);
        } catch (IOException e) {
            log.info(username + "下线的时候通知所有人发生了错误");
        }
        log.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }


    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}

