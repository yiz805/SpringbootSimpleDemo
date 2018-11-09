package com.ding.hddk.elasticsearch.impl;

import com.ding.hddk.elasticsearch.BookES;
import com.ding.hddk.elasticsearch.client.ElasticSearchClientUtil;
import com.ding.hddk.elasticsearch.entity.BookDO;
import com.ding.hddk.elasticsearch.util.SearchUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookESImpl implements BookES {
    @Override
    public List<BookDO> getBooks() {
        String dsl = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"bookname\": \"java\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String endpoint = "/commodity/_search";
        JsonNode jsonNode = ElasticSearchClientUtil.sendPostJsonNode(endpoint, dsl);
        JsonNode hitArray = ElasticSearchClientUtil.getHitsArray(jsonNode);
        List<BookDO> books = new ArrayList<>();
        for (int i = 0; i < hitArray.size(); i++) {
            BookDO book = SearchUtil.transformBookDo(hitArray.get(i));
            books.add(book);
        }
        return books;
    }

    @Override
    public void addBook(BookDO bookDO) {
        String endpoint = "/commodity/book";
        String dsl = "{\n" +
                "\t\"bookname\": \"" + bookDO.getBookname() + "\",\n" +
                "\t\"booktype\":\"" + bookDO.getBooktype() + "\",\n" +
                "\t\"bookpress\":\"" + bookDO.getBookpress() + "\",\n" +
                "\t\"bookprice\":" + bookDO.getBookprice() + "\n" +
                "}";
        JsonNode jsonNode = ElasticSearchClientUtil.sendPostJsonNode(endpoint, dsl);
    }

    @Override
    public void updateBook(BookDO bookDO) {
        String endpoint = "/commodity/book/" + bookDO.getId() + "/_update";
        String dsl = "{\n \"doc\":{";
        if (bookDO.getBookname() != null) {
            dsl += "\t\"bookname\": \"" + bookDO.getBookname() + "\",\n";
        }
        if (bookDO.getBooktype() != null) {
            dsl += "\t\"booktype\":\"" + bookDO.getBooktype() + "\",\n";
        }
        if (bookDO.getBookpress() != null) {
            dsl += "\t\"bookpress\":\"" + bookDO.getBookpress() + "\",\n";
        }
        if (bookDO.getBookprice() != null) {
            dsl += "\t\"bookprice\":" + bookDO.getBookprice() + "\n" + "}}";
        }
        JsonNode jsonNode = ElasticSearchClientUtil.sendPostJsonNode(endpoint, dsl);
    }

    @Override
    public void deleteBook(String id) {
        String endpoint = "/commodity/book/" + id;
        String dsl = "";
        ElasticSearchClientUtil.sendPost(endpoint, dsl);
    }
}
