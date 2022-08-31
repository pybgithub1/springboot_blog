package com.atguigu.springbootcrud.service.impl;

import com.atguigu.springbootcrud.domain.Msg;
import com.atguigu.springbootcrud.mapper.MsgMapper;
import com.atguigu.springbootcrud.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private MsgMapper msgMapper;

    @Override
    public List<Msg> findMsgByFromuserAndTouser(String fromuser, String touser) {
        return msgMapper.findMsgByFromuserAndTouser(fromuser, touser);
    }

    @Override
    public Boolean insertMsgByFromuserAndTouserAndMsg(String fromuser, String touser, String msg, String time) {

        return msgMapper.insertMsgByFromuserAndTouserAndMsg(fromuser, touser, msg, time);
    }

    @Override
    public List<Msg> findMsgsGunDongBack(String fromuser, String touser, String mid) {
        List<Msg> msgs = new ArrayList<>();
        List<Msg> msgs1 = msgMapper.findMsgByFromuserAndTouser(fromuser, touser);
        List<Msg> msgs2 = msgMapper.findMsgByFromuserAndTouser(touser, fromuser);
        msgs.addAll(msgs1);
        msgs.addAll(msgs2);
        msgs.sort(new Comparator<Msg>() {
            @Override
            public int compare(Msg msg1, Msg msg2) {
                return msg1.getTime().compareTo(msg2.getTime());
            }
        });
        if (msgs.size() <= 7) {
            return msgs;
        } else {
            List<Msg> msgs3 = null;
            int i = Integer.parseInt(mid); /*i=811;810，809，808，807，806，805，804，803都不是两个用户的聊天内容*/
            int s;
            int j = 0;
            int sum = msgs.size();
            /* j应该是802但是现在j等于811-1=810;*/
            for (int i1 = 0; i1 < sum; i1++) {
                if (msgs.get(i1).getMid() == i) {
                    s = i1 - 1;
                    if (s >= 0) {
                        j = msgs.get(s).getMid();
                    } else {
                        j = i;
                    }
//                    System.out.println(i);
//                    System.out.println(s);
//                    System.out.println(j);
//                    System.out.println(sum);
                    break;
                }
            }
            for (int i1 = 0; i1 < sum; i1++) {
                if (msgs.get(i1).getMid() == j) {
                    if (i1 >= 6) {
//                        System.out.println("a");
                        msgs3 = msgs.subList(i1 - 6, i1 + 1);
                    } else if (i1 >= 5) {
//                        System.out.println("b");
                        msgs3 = msgs.subList(i1 - 5, i1 + 2);
                    } else if (i1 >= 4) {
//                        System.out.println("c");
                        msgs3 = msgs.subList(i1 - 4, i1 + 3);
                    } else if (i1 >= 3) {
//                        System.out.println("d");
                        msgs3 = msgs.subList(i1 - 3, i1 + 4);
                    } else if (i1 >= 2) {
//                        System.out.println("e");
                        msgs3 = msgs.subList(i1 - 2, i1 + 5);
                    } else if (i1 >= 1) {
//                        System.out.println("f");
                        msgs3 = msgs.subList(i1 - 1, i1 + 6);
                    } else if (i1 >= 0) {
//                        System.out.println("g");
                        msgs3 = msgs.subList(i1, i1 + 7);
                    }
                    break;
                } else if (i1 == sum - 1) {
//                    System.out.println("h");
                    msgs3 = msgs.subList(0, 7);
                }
            }
            System.out.println(msgs3);
            return msgs3;
        }


    }

    @Override
    public List<Msg> findMsgsGunDongForword(String fromuser, String touser, String mid) {
        List<Msg> msgs = new ArrayList<>();
        List<Msg> msgs1 = msgMapper.findMsgByFromuserAndTouser(fromuser, touser);
        List<Msg> msgs2 = msgMapper.findMsgByFromuserAndTouser(touser, fromuser);
        msgs.addAll(msgs1);
        msgs.addAll(msgs2);
        msgs.sort(new Comparator<Msg>() {
            @Override
            public int compare(Msg msg1, Msg msg2) {
                return msg1.getTime().compareTo(msg2.getTime());
            }
        });
        if (msgs.size() <= 7) {
            return msgs;
        } else {

            List<Msg> msgs4 = null;
            int i = Integer.parseInt(mid); /*i=811;810，809，808，807，806，805，804，803都不是两个用户的聊天内容*/
            int s;
            int j = 0;
            int sum = msgs.size();
            /* j应该是802但是现在j等于811-1=810;*/
            for (int i1 = 0; i1 < sum; i1++) {
                if (msgs.get(i1).getMid() == i) {
                    s = i1 + 1;
                    if (s <= sum - 1) {
                        j = msgs.get(s).getMid();
                    } else {
                        j = i;
                    }
//                    System.out.println(i);
//                    System.out.println(s);
//                    System.out.println(j);
//                    System.out.println(sum);
                    break;
                }
            }

            for (int i1 = 0; i1 < sum; i1++) {
                if (msgs.get(i1).getMid() == j) {
                    if (sum - i1 >= 6) {
//                        System.out.println("a");
                        msgs4 = msgs.subList(i1 - 1, i1 + 6);
                    } else if (sum - i1 >= 5) {
//                        System.out.println("b");
                        msgs4 = msgs.subList(i1 - 2, i1 + 5);
                    } else if (sum - i1 >= 4) {
//                        System.out.println("c");
                        msgs4 = msgs.subList(i1 - 3, i1 + 4);
                    } else if (sum - i1 >= 3) {
//                        System.out.println("d");
                        msgs4 = msgs.subList(i1 - 4, i1 + 3);
                    } else if (sum - i1 >= 2) {
//                        System.out.println("e");
                        msgs4 = msgs.subList(i1 - 5, i1 + 2);
                    } else if (sum - i1 >= 1) {
//                        System.out.println("f");
                        msgs4 = msgs.subList(i1 - 6, i1 + 1);
                    } else if (sum - i1 >= 0) {
//                        System.out.println("g");
                        msgs4 = msgs.subList(i1 - 7, i1);
                    }
                    break;
                } else if (i1 == sum - 1) {
//                    System.out.println("h");
                    msgs4 = msgs.subList(sum - 7, sum);
                }
            }
            System.out.println(msgs4);
            return msgs4;
        }
    }

}
