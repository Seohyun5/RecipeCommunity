package com.spring.biz.paging;

public class PagingVO {
	
	//startPage : 한 화면에서 시작하는 페이지 넘버
	//endPage : 한 화면에서 끝나는 페이지 넘버
	private int nowPage, startPage, endPage, total, lastPage, start, end;
	private boolean prev, next;
	private int prevPage, nextPage;
	private int cntPerPage = 9;
	private String category;
	private String keyword;
	
	public PagingVO() {}

	public PagingVO(int total, int nowPage) {
		setTotal(total);
		setNowPage(nowPage);
		calcData(total, nowPage, cntPerPage);
		calcStartEndPage(nowPage);
	}
	
	public PagingVO(int total, int nowPage, String category) {
		setTotal(total);
		setNowPage(nowPage);
		setCategory(category);
		calcData(total, nowPage, cntPerPage);
		calcStartEndPage(nowPage);
	}
	
	public PagingVO(String keyword, int total, int nowPage) {
		setKeyword(keyword);
		setTotal(total);
		setNowPage(nowPage);
		calcData(total, nowPage, cntPerPage);
		calcStartEndPage(nowPage);
	}
	
	
	public void calcData(int total, int nowPage, int cntPerPage) {
		setLastPage((int)Math.ceil((double)total/(double)cntPerPage));
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
		
		if(nowPage-1 >= 1) {
			setPrev(true);
			setPrevPage(nowPage-1);
		}else {setPrev(false);}
		if(nowPage+1 <= getLastPage()) {
			setNext(true);
			setNextPage(nowPage+1);
		}else {setNext(false);}
	}
	
	public void calcStartEndPage(int nowPage) {
		setEndPage(((int)Math.ceil((double)nowPage/(double)10)) * 10);
		setStartPage(getEndPage() - 9);
		
		if(getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		if(getStartPage() < 1) {
			setStartPage(1);
		}
	}
	

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean getPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean getNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	public int getPrevPage() {
		return prevPage;
	}
	
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	
	public int getNextPage() {
		return nextPage;
	}
	
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}