package com.jy.mapper.book;

import com.jy.model.book.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select t_id bookID, t_name bookName,t_author bookAuthor," +
            " t_type bookType,t_price bookPrice,t_desc bookDesc," +
            " date_format(t_date, '%Y-%m-%d') bookDateStr" +
            " from t_books" +
            " where t_status = 1")
    List<Book> selectBookList(Book book);

    @Insert("insert into t_books (t_name, t_type, t_author,t_price, t_desc, t_date)" +
            " values " +
            " (#{bookName}, #{bookType}, #{bookAuthor},#{bookPrice}," +
            "  #{bookDesc}, str_to_date(#{bookDateStr}, '%Y-%m-%d'))")
    void insertBookInfo(Book book);

    @Update("<script>" +
            " update t_books set" +
            " t_status = 2" +
            " where t_id in" +
            " <foreach collection='bookIDS.split(\",\")' item='item' separator=',' open='(' close=')'>" +
            " #{item}" +
            " </foreach>" +
            " </script>")
    void deleteAllCheckedBook(Book book);

    @Select("select t_id bookID, t_name bookName,t_author bookAuthor," +
            " t_type bookType,t_price bookPrice,t_desc bookDesc," +
            " date_format(t_date, '%Y-%m-%d') bookDateStr" +
            " from t_books" +
            " where t_id = #{bookID}")
    Book selectBookByID(Book book);

    @Update("update t_books set " +
            " t_name = #{bookName},t_author = #{bookAuthor}," +
            " t_type = #{bookType},t_price = #{bookPrice}," +
            " t_desc = #{bookDesc},t_date = STR_TO_DATE(#{bookDateStr}, '%Y-%m-%d')" +
            " where t_id = #{bookID}")
    void updateBookInfoByBookID(Book book);
}
