package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.LogEntityDao;
import com.qdu.pojo.Admin;
import com.qdu.pojo.Feedback;
import com.qdu.pojo.LogEntity;
import com.qdu.service.LogEntityService;

@Transactional
@Service
public class LogEntityServiceImpl implements LogEntityService{

	@Autowired LogEntityDao logEntityDaoImpl;
	
	@Override
	public void saveLog(LogEntity logEntity) {
		logEntityDaoImpl.saveLog(logEntity);
	}
  
	@Override 
	public List<LogEntity> selectLog(int startPos) {
		return logEntityDaoImpl.selectLog(startPos);
	}

	@Override
	public Admin selectAdminById(String adminId) {
		return logEntityDaoImpl.selectAdminById(adminId);
	}

	@Override
	public int selectLogEntityCount() {
		return logEntityDaoImpl.selectLogEntityCount();
	}

	@Override
	public List<LogEntity> selectStudentLog(String userId) {
		return logEntityDaoImpl.selectStudentLog(userId);
	}

	@Override
	public List<LogEntity> selectStudentLogByTime(String userId, String dateTime,String coreKey) {
		return logEntityDaoImpl.selectStudentLogByTime(userId, dateTime,coreKey);
	}

	@Override
	public List<LogEntity> selectTeacherLogEntity() {
		return logEntityDaoImpl.selectTeacherLogEntity();
	}

	@Override
	public List<LogEntity> selectStudentLogEntity() {
		return logEntityDaoImpl.selectStudentLogEntity();
	}

	@Override
	public int insertFeedback(Feedback feedback) {
		return logEntityDaoImpl.insertFeedback(feedback);
	}

	@Override
	public List<Feedback> selectFeedback() {
		return logEntityDaoImpl.selectFeedback();
	}

	@Override
	public int updateFeedbackOfReson(int feedbackId, String reson, String result) {
		return logEntityDaoImpl.updateFeedbackOfReson(feedbackId, reson, result);
	}

}
