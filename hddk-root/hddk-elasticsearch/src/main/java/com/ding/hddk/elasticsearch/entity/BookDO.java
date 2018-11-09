package com.ding.hddk.elasticsearch.entity;

import lombok.Data;

@Data
public class BookDO {
    private String id;
    private String bookname;
    private String booktype;
    private String bookpress;
    private Integer bookprice;
}
