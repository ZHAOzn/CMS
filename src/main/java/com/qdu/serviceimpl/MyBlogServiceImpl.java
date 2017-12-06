package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.MyBlogDao;
import com.qdu.pojo.MyBlog;
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
	
	

}
