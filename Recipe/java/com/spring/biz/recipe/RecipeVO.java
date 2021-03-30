package com.spring.biz.recipe;

import java.util.Date;

public class RecipeVO {
	private int recipeno;
	private String category;
	private String subject;
	private String written;
	private String content;
	private Date date;
//	private int count;
	private String id;
	
	public RecipeVO() {
		super();
	}

	public RecipeVO(int recipeno, String category, String subject, String written, String content,
			Date date, String id) {
		super();
		this.recipeno = recipeno;
		this.category = category;
		this.subject = subject;
		this.written = written;
		this.content = content;
		this.date = date;
//		this.count = count;
		this.id = id;
	}

	public int getRecipeno() {
		return recipeno;
	}

	public void setRecipeno(int recipeno) {
		this.recipeno = recipeno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWritten() {
		return written;
	}

	public void setWritten(String written) {
		this.written = written;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}