package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.MyBlogDao;
import com.qdu.pojo.MyBlog;
import com.qdu.pojo.MyBlogUp;

@Repository
public class MyBlogDaoImpl implements MyBlogDao{

	@Autowired private SqlSessionFactory sqlSessionFactory;

	@Override
	public MyBlog selectMyBlogById(int blogId) {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogById";
		return sqlSessionFactory.openSession().selectOne(statement, blogId);
	}

	@Override
	public List<MyBlog> selectMyBlogByUserId(String blogAuthor) {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogByUserId";
		return sqlSessionFactory.openSession().selectList(statement, blogAuthor);
	}

	@Override
	public int insertMyBlog(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.insertMyBlog";
		return sqlSessionFactory.openSession().insert(statement, myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByHot(String blogAuthor) {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogByHot";
		return sqlSessionFactory.openSession().selectList(statement, blogAuthor);
	}

	@Override
	public int deleteMyBlogById(int blogId) {
		String statement = "com.qdu.mapping.MyBlogMapping.deleteMyBlogById";
		return sqlSessionFactory.openSession().delete(statement, blogId);
	}

	@Override
	public int updateBlog(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.updateBlog";
		return sqlSessionFactory.openSession().update(statement, myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByConCat(String blogTitle) {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogByConCat";
		return sqlSessionFactory.openSession().selectList(statement, blogTitle);
	}

	@Override
	public List<MyBlog> selectMyBlogByVerify() {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogByVerify";
		return sqlSessionFactory.openSession().selectList(statement);
	}

	@Override
	public int updateBlogofVerify(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.updateBlogofVerify";
		return sqlSessionFactory.openSession().update(statement, myBlog);
	}

	@Override
	public List<MyBlog> selectMyBlogByNoFilter() {
		String statement = "com.qdu.mapping.MyBlogMapping.selectMyBlogByNoFilter";
		return sqlSessionFactory.openSession().selectList(statement);
	}

	@Override
	public List<MyBlog> studentSearchBlog(String blogContent, String belongTo) {
		String statement = "com.qdu.mapping.MyBlogMapping.studentSearchBlog";
		Map<String, Object> map = new HashMap<>();
		map.put("blogContent", blogContent);
		map.put("belongTo", belongTo);
		return sqlSessionFactory.openSession().selectList(statement,map);
	}

	@Override
	public int updateBlogofUp(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.updateBlogofUp";
		return sqlSessionFactory.openSession().update(statement, myBlog);
	}

	@Override
	public int updateBlogofDown(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.updateBlogofDown";
		return sqlSessionFactory.openSession().update(statement, myBlog);
	}

	@Override
	public int updateBlogofHotClick(MyBlog myBlog) {
		String statement = "com.qdu.mapping.MyBlogMapping.updateBlogofHotClick";
		return sqlSessionFactory.openSession().update(statement, myBlog);
	}

	@Override
	public int insertMyBlogUp(MyBlogUp myBlogUp) {
		String statement = "com.qdu.mapping.MyBlogUpMapping.insertMyBlogUp";
		return sqlSessionFactory.openSession().insert(statement,myBlogUp);
	}

	@Override
	public MyBlogUp selectMyBlogUp(String userId, int blogId) {
		String statement = "com.qdu.mapping.MyBlogUpMapping.selectMyBlogUp";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("blogId", blogId);
		return sqlSessionFactory.openSession().selectOne(statement,map);
	}

	@Override
	public int deleteMyBlogUp(int myBlogUpId) {
		String statement = "com.qdu.mapping.MyBlogUpMapping.deleteMyBlogUp";
		return sqlSessionFactory.openSession().delete(statement,myBlogUpId);
	}
	
	
}
