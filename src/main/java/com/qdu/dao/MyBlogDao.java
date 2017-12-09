package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.MyBlog;

public interface MyBlogDao {
	
	public MyBlog selectMyBlogById(int blogId);
	
	public List<MyBlog> selectMyBlogByUserId(String blogAuthor);
	
	public int insertMyBlog(MyBlog myBlog);
	
	public List<MyBlog> selectMyBlogByHot(String blogAuthor);
	
	public int deleteMyBlogById(int blogId);
	
	public int updateBlog(MyBlog myBlog);
	
	public List<MyBlog> selectMyBlogByConCat(String blogTitle);
	
	public List<MyBlog> selectMyBlogByVerify();
	
	public int updateBlogofVerify(MyBlog myBlog);

}
