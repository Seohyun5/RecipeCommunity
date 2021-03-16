package com.spring.biz.blog;

import java.util.Date;

public class BlogVO {
	private int blogno;
	private String subject;
	private String written;
	private String content;
	private String filename;
	private Date date;
	private int count;
	private String id;
	
	public BlogVO() {
	}

	public BlogVO(int blogno, String subject, String written, String content, String filename, Date date, int count, String id) {
		super();
		this.blogno = blogno;
		this.subject = subject;
		this.written = written;
		this.content = content;
		this.filename = filename;
		this.date = date;
		this.count = count;
		this.id = id;
	}

	public int getBlogno() {
		return blogno;
	}

	public void setBlogno(int blogno) {
		this.blogno = blogno;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}
	
	public void setCount(String id) {
		this.id = id;
	}
	
}