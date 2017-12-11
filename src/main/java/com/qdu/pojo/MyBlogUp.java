package com.qdu.pojo;

import java.io.Serializable;

public class MyBlogUp implements Serializable{
	
	private int myBlogUpId;
	private String userId;
	private int blogId;
	
	
	
	public int getMyBlogUpId() {
		return myBlogUpId;
	}
	public void setMyBlogUpId(int myBlogUpId) {
		this.myBlogUpId = myBlogUpId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	
	

}
