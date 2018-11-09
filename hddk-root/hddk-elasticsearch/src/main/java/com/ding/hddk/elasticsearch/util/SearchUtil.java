package com.ding.hddk.elasticsearch.util;

import com.ding.hddk.elasticsearch.entity.BookDO;
import com.fasterxml.jackson.databind.JsonNode;

public class SearchUtil {
    /**
     * 将jsonNode转换成实体
     *
     * @param jsonNode
     * @return
     */
    public static BookDO transformBookDo(JsonNode jsonNode) {
        BookDO book = new BookDO();
        JsonNode sourceJsonNode = jsonNode.get("_source");

        book.setId(jsonNode.get("_id").asText());
        book.setBookname(sourceJsonNode.get("bookname").asText());
        book.setBooktype(sourceJsonNode.get("booktype").asText());
        book.setBookpress(sourceJsonNode.get("bookpress").asText());
        book.setBookprice(sourceJsonNode.get("bookprice").asInt());
        return book;
    }
}
