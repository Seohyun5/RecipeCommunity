package com.spring.biz.recipe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class RecipeImageVO {
	
	private int imageFileNO;
	private String imageFileName;
	private Date regDate;
	private int recipeno;
	
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
	public int getRecipeno() {
		return recipeno;
	}
	public void setRecipeno(int recipeno) {
		this.recipeno = recipeno;
	}
	
}
