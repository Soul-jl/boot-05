package com.jy.service.book.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.book.BookMapper;
import com.jy.model.book.Book;
import com.jy.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Map<String, Object> selectBookList(Book book) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //开启分页
        Page<Object> page = PageHelper.startPage(book.getPage(), book.getLimit());
        List<Book> list = bookMapper.selectBookList(book);
        //转化

        //获取总条数
        long total = page.getTotal();

        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", total);
        return map;
    }

    @Override
    public void insertBookInfo(Book book) {
        bookMapper.insertBookInfo(book);
    }


    @Override
    public void deleteAllCheckedBook(Book book) {
       // String bookIDS = book.getBookIDS();
//		String[] split = bookIDS.split(",");
//		List<Integer> bookIDList = new ArrayList<>();
//		for (String string : split) {
//			bookIDList.add(Integer.parseInt(string));
//		}
//		book.setBookIDList(bookIDList);
        bookMapper.deleteAllCheckedBook(book);
    }


    @Override
    public Book selectBookByID(Book book) {
        return bookMapper.selectBookByID(book);
    }

    @Override
    public void updateBookInfoByBookID(Book book) {
        bookMapper.updateBookInfoByBookID(book);
    }
}
