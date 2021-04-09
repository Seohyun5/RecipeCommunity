package com.spring.biz.recipe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class RecipeImageVO {
	
	private int rimageFileNO;
	private String rimageFileName;
	private int recipeno;
	
	public int getRimageFileNO() {
		return rimageFileNO;
	}
	public void setRimageFileNO(int rimageFileNO) {
		this.rimageFileNO = rimageFileNO;
	}
	public String getRimageFileName() {
		return rimageFileName;
	}
	public void setRimageFileName(String rimageFileName) {
		try {
			this.rimageFileName = URLEncoder.encode(rimageFileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public int getRecipeno() {
		return recipeno;
	}
	public void setRecipeno(int recipeno) {
		this.recipeno = recipeno;
	}
	
}
