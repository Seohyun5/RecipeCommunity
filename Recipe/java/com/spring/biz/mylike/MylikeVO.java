package com.spring.biz.mylike;

public class MylikeVO {
	private String id;
	private int recipeno;
	
	public MylikeVO() {
	}

	public MylikeVO(String id, int recipeno) {
		this.id = id;
		this.recipeno = recipeno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRecipeno() {
		return recipeno;
	}

	public void setRecipeno(int recipeno) {
		this.recipeno = recipeno;
	}
	
	
}