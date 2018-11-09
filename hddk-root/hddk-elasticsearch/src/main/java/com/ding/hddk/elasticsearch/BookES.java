package com.ding.hddk.elasticsearch;

import com.ding.hddk.elasticsearch.entity.BookDO;

import java.util.List;


public interface BookES {
    /**
     * 查询所有书
     *
     * @return
     */
    List<BookDO> getBooks();

    /**
     * 添加
     *
     * @param bookDO
     */
    void addBook(BookDO bookDO);

    /**
     * 修改
     *
     * @param bookDO
     */
    void updateBook(BookDO bookDO);

    /**
     * 删除(改)
     *
     * @param id
     */
    void deleteBook(String id);
}
