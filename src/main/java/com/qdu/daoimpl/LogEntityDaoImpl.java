package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.LogEntityDao;
import com.qdu.pojo.Admin;
import com.qdu.pojo.Feedback;
import com.qdu.pojo.LogEntity;

@Repository
public class LogEntityDaoImpl implements LogEntityDao{

	@Autowired private SqlSessionFactory sessionFactory;
	 
	@Override 
	public void saveLog(LogEntity logEntity) {
		String statement="com.qdu.mapping.LogEntityMapping.insertLogEntity";
		sessionFactory.openSession().insert(statement,logEntity);
	} 

	@Override
	public List<LogEntity> selectLog(int startPos) {
		String statement = "com.qdu.mapping.LogEntityMapping.selectLog";
		return sessionFactory.openSession().selectList(statement,startPos);
	}

	@Override
	public Admin selectAdminById(String adminId) {
		String statement = "com.qdu.mapping.AdminMapping.selectAdminById";
		return sessionFactory.openSession().selectOne(statement, adminId);
	}

	@Override
	public int selectLogEntityCount() {
		String statement = "com.qdu.mapping.LogEntityMapping.selectLogEntityCount";
		return sessionFactory.openSession().selectOne(statement);
	}

	@Override
	public List<LogEntity> selectStudentLog(String userId) {
		String statement = "com.qdu.mapping.LogEntityMapping.selectStudentLog";
		return sessionFactory.openSession().selectList(statement, userId);
	}

	@Override
	public List<LogEntity> selectStudentLogByTime(String userId, String dateTime,String coreKey) {
		String statement = "com.qdu.mapping.LogEntityMapping.selectStudentLogByTime";
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("dateTime", dateTime);
		map.put("coreKey", coreKey);
		return sessionFactory.openSession().selectList(statement, map);
	}

	@Override
	public List<LogEntity> selectTeacherLogEntity() {
		String statement = "com.qdu.mapping.LogEntityMapping.selectTeacherLogEntity";
		return sessionFactory.openSession().selectList(statement);
	}

	@Override
	public List<LogEntity> selectStudentLogEntity() {
		String statement = "com.qdu.mapping.LogEntityMapping.selectStudentLogEntity";
		return sessionFactory.openSession().selectList(statement);
	}

	@Override
	public int insertFeedback(Feedback feedback) {
		String statement = "com.qdu.mapping.FeedbackMapping.insertFeedback";
		return sessionFactory.openSession().insert(statement,feedback);
	}

	@Override
	public List<Feedback> selectFeedback() {
		String statement = "com.qdu.mapping.FeedbackMapping.selectFeedback";
		return sessionFactory.openSession().selectList(statement);
	}

	@Override
	public int updateFeedbackOfReson(int feedbackId, String reson, String result) {
		String statement = "com.qdu.mapping.FeedbackMapping.updateFeedbackOfReson";
		Map<String, Object> map = new HashMap<>();
		map.put("feedbackId", feedbackId);
		map.put("reson", reson);
		map.put("result", result);
		return sessionFactory.openSession().update(statement,map);
	}

}
