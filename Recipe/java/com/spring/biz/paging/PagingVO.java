package com.spring.biz.paging;

public class PagingVO {
	
	//startPage : 한 화면에서 시작하는 페이지 넘버
	//endPage : 한 화면에서 끝나는 페이지 넘버
	private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end;
	
	public PagingVO() {}

	public PagingVO(int total, int nowPage, int cntPerPage) {
		setTotal(total);
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage());
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int)Math.ceil((double)total/(double)cntPerPage));
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
	
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
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

//	public int getCntPage() {
//		return cntPage;
//	}
//
//	public void setCntPage(int cntPage) {
//		this.cntPage = cntPage;
//	}
	
	
}