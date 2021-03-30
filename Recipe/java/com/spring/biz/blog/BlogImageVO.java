package com.spring.biz.blog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class BlogImageVO {
	
	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int blogno;
	
	public int getImageFileNO() {
		return imageFileNO;
	}
	public void setImageFileNO(int imageFileNO) {
		this.imageFileNO = imageFileNO;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		try {
			this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getBlogno() {
		return blogno;
	}
	public void setBlogno(int blogno) {
		this.blogno = blogno;
	}
	
}
