package com.hibernate.helloworld;

import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private String author;
	private Date data;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public News(String title, String author, Date data) {
		super();
		this.title = title;
		this.author = author;
		this.data = data;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public News() {
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author
				+ ", data=" + data + "]";
	}
	

}
