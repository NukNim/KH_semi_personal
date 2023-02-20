package com.kh.board.boardDTO;

import java.sql.Date;

public class CommentDTO {
	
//	COMMENT_ID   NOT NULL NUMBER         
//	REF_ID       NOT NULL NUMBER         
//	USER_ID               VARCHAR2(50)   
//	USER_PW               VARCHAR2(100)  
//	CONTEXT               VARCHAR2(2000) 
//	REG_DATE              TIMESTAMP(6)   
//	COMMENT_STEP          NUMBER         
//	COMM_REF_ID           NUMBER         

	private int commentId;
	private int refId;
	private String userId;
	private String userPw;
	private String context;
	private Date regDate;
	private int commentStep;
	private int commentRefId;
	
	
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCommentStep() {
		return commentStep;
	}
	public void setCommentStep(int commentStep) {
		this.commentStep = commentStep;
	}
	public int getCommentRefId() {
		return commentRefId;
	}
	public void setCommentRefId(int commentRefId) {
		this.commentRefId = commentRefId;
	}
	
	
	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", refId=" + refId + ", userId=" + userId + ", userPw=" + userPw
				+ ", context=" + context + ", regDate=" + regDate + ", commentStep=" + commentStep + ", commentRefId="
				+ commentRefId + "]";
	}
	
	
	
	
	
	

}
