package com.kosa.myapp3.comment;

public class CommentDto {
	private String board_seq;
	private String seq;
	private String user_id;
	private String comments;
	private String regdate;
	private String delyn;

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDelyn() {
		return delyn;
	}

	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}

}
