package com.jy.controller.book;

import com.jy.model.book.Book;
import com.jy.service.book.BookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("book/toList")
    String toListPage() {
        return "book/list";
    }

    //查询书籍列表
    @RequestMapping("book/list")
    @ResponseBody
    Map<String, Object> selectBookList(Book book) {
        Map<String, Object> map = bookService.selectBookList(book);
        return map;
    }

    @RequestMapping("book/toAdd")
    String toAddBookPage(Book book) {
        return "book/add";
    }

    @RequestMapping("book/insert")
    @ResponseBody
    String insertBookInfo(Book book) {
        bookService.insertBookInfo(book);
        return "{}";
    }

    @RequiresPermissions(value = {"/book/deleteAll"})
    @RequestMapping("book/deleteAll")
    @ResponseBody
    String deleteAllCheckedBook(Book book) {
        bookService.deleteAllCheckedBook(book);
        return "{}";
    }

    @RequestMapping("book/toEdit")
    String selectBookByID(Book book, ModelMap mm) {
        Book b = bookService.selectBookByID(book);
        mm.addAttribute("book", b);
        return "book/edit";
    }


    @RequestMapping("book/update")
    @ResponseBody
    String updateBookInfoByXmllID(Book book) {
        bookService.updateBookInfoByBookID(book);
        return "{}";
    }


}
