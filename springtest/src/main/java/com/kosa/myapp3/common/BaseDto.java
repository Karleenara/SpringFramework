package com.kosa.myapp3.common;

public class BaseDto {
	protected int pg;
	protected int pgSize = 10;


	protected String searchGbn = "";
	protected String searchKeyword = "";
	protected int num = 0;

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getPgSize() {
		return pgSize;
	}

	public void setPgSize(int pgSize) {
		this.pgSize = pgSize;
	}

	public String getSearchGbn() {
		return searchGbn;
	}

	public void setSearchGbn(String searchGbn) {
		this.searchGbn = searchGbn;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
