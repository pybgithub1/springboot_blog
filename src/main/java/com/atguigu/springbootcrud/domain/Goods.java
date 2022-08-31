package com.atguigu.springbootcrud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    //商品编号
    private int gid;
    //商品名称
    private String name;
    //商品价格
    private Double price;
    //商品类型
    private String type;
    //商品数量
    private int amount;



}
