package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.MyBlogDao;
import com.qdu.pojo.MyBlog;
import com.qdu.pojo.MyBlogUp;
import com.qdu.service.MyBlogService;

@Service
@Transactional
public class MyBlogServiceImpl implements MyBlogService{
	@Autowired private MyBlogDao myBlogDaoImpl;

	@Override
	public MyBlog selectMyBlogById(int blogId) {
		return myBlogDaoImpl.selectMyBlogById(blogId);
	}

	@Override
	public List<MyBlog> selectMyBlogByUserId(String blogAuthor) {
		return myBlogDaoImpl.selectMyBlogByUserId(blogAuthor);
	}

	@Override
	public int insertMyBlog(MyBlog myBlog) {
		return myBlogDaoImpl.insertMyBlog(myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByHot(String blogAuthor) {
		return myBlogDaoImpl.selectMyBlogByHot(blogAuthor);
	}

	@Override
	public int deleteMyBlogById(int blogId) {
		return myBlogDaoImpl.deleteMyBlogById(blogId);
	}

	@Override
	public int updateBlog(MyBlog myBlog) {
		return myBlogDaoImpl.updateBlog(myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByConCat(String blogTitle) {
		return myBlogDaoImpl.selectMyBlogByConCat(blogTitle);
	}

	@Override
	public List<MyBlog> selectMyBlogByVerify() {
		return myBlogDaoImpl.selectMyBlogByVerify();
	}

	@Override
	public int updateBlogofVerify(MyBlog myBlog) {
		return myBlogDaoImpl.updateBlogofVerify(myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByNoFilter() {
		return myBlogDaoImpl.selectMyBlogByNoFilter();
	}

	@Override
	public List<MyBlog> studentSearchBlog(String blogContent, String belongTo) {
		return myBlogDaoImpl.studentSearchBlog(blogContent, belongTo);
	}

	@Override
	public int updateBlogofUp(MyBlog myBlog) {
		return myBlogDaoImpl.updateBlogofUp(myBlog);
	}

	@Override
	public int updateBlogofDown(MyBlog myBlog) {
		return myBlogDaoImpl.updateBlogofDown(myBlog);
	}

	@Override
	public int updateBlogofHotClick(MyBlog myBlog) {
		return myBlogDaoImpl.updateBlogofHotClick(myBlog);
	}

	@Override
	public int insertMyBlogUp(MyBlogUp myBlogUp) {
		return  myBlogDaoImpl.insertMyBlogUp(myBlogUp);
	}

	@Override
	public MyBlogUp selectMyBlogUp(String userId, int blogId) {
		return myBlogDaoImpl.selectMyBlogUp(userId, blogId);
	}

	@Override
	public int deleteMyBlogUp(int myBlogUpId) {
		return myBlogDaoImpl.deleteMyBlogUp(myBlogUpId);
	}
	
	

}
