package com.spring.biz.recipe;

public class RecipeImageVO {
	
	private int rimageFileNO;
	private String rimageFileName;
	private int recipeno;
	
	public RecipeImageVO() {
		super();
	}
	
	public RecipeImageVO(int rimageFileNO, String rimageFileName, int recipeno) {
		super();
		this.rimageFileNO = rimageFileNO;
		this.rimageFileName = rimageFileName;
		this.recipeno = recipeno;
	}

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
		this.rimageFileName = rimageFileName;
	}

	public int getRecipeno() {
		return recipeno;
	}

	public void setRecipeno(int recipeno) {
		this.recipeno = recipeno;
	}
	

}
