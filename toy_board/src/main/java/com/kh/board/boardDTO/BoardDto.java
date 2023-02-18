package com.kh.board.boardDTO;

import java.sql.Date;

public class BoardDto {

	private 	int	id;
	private 	String	title;
	private 	String	content;
	private 	String	userId;
	private 	String	userPw;
	private 	Date	createDate;
	private 	Date	modifyDate;
	private 	String	delFlag;
	private 	int	categoryId;
	private 	String categoryName;
	private 	int viewCnt;
	
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", userPw="
				+ userPw + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", delFlag=" + delFlag
				+ ", categoryId=" + categoryId + "]";
	}
	
	
	

	

}
