package com.qdu.daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.MyBlogDao;
import com.qdu.pojo.MyBlog;

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
	
	
}
