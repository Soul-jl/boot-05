/** 
 * <pre>项目名称:ssm-model 
 * 文件名称:Book.java 
 * 包名:com.jy.model.book 
 * 创建日期:2020年3月30日下午4:21:10 
 * Copyright (c) 2020, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jy.model.book;

import java.io.Serializable;
import java.util.List;

import com.jy.common.PageUtil;

/** 
 * <pre>项目名称：ssm-model    
 * 类名称：Book    
 * 类描述：    
 * 创建人：靳利 chenjh123@gmail.com    
 * 创建时间：2020年3月30日 下午4:21:10    
 * 修改人：靳利 chenjh123@gmail.com     
 * 修改时间：2020年3月30日 下午4:21:10    
 * 修改备注：       
 * @version </pre>    
 */
public class Book extends PageUtil implements Serializable{

	private int bookID;
	
	private String bookName;
	
	private String bookAuthor;
	
	private int bookType;
	
	private float bookPrice;
	
	private String bookDesc;
	
	private String bookDateStr;
	
	/** 业务字段 */
	private String bookIDS;
	
	private List<Integer> bookIDList;

	public String getBookIDS() {
		return bookIDS;
	}

	public void setBookIDS(String bookIDS) {
		this.bookIDS = bookIDS;
	}

	public List<Integer> getBookIDList() {
		return bookIDList;
	}

	public void setBookIDList(List<Integer> bookIDList) {
		this.bookIDList = bookIDList;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookType() {
		return bookType;
	}

	public void setBookType(int bookType) {
		this.bookType = bookType;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getBookDateStr() {
		return bookDateStr;
	}

	public void setBookDateStr(String bookDateStr) {
		this.bookDateStr = bookDateStr;
	}
	
}
