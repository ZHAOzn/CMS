package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.MyBlog;
import com.qdu.pojo.MyBlogUp;

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
	
	public List<MyBlog> selectMyBlogByNoFilter();
	
	public List<MyBlog> studentSearchBlog(String blogContent,String belongTo);
	
	public int updateBlogofUp(MyBlog myBlog);
	
	public int updateBlogofDown(MyBlog myBlog);
	
	public int updateBlogofHotClick(MyBlog myBlog);
	
	public int insertMyBlogUp(MyBlogUp myBlogUp);
	
	public MyBlogUp selectMyBlogUp(String userId,int blogId);
	
	public int deleteMyBlogUp(int myBlogUpId);
	
	
	
	

}
