package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.LeaveRecordDao;
import com.qdu.daoimpl.LeaveRecordDaoImpl;
import com.qdu.pojo.LeaveRecord;
import com.qdu.service.LeaveRecordService;

@Transactional
@Service
public class LeaveRecordServiceImpl implements LeaveRecordService{
	
	@Autowired private LeaveRecordDao leaveRecordDaoImpl;

	@Override
	public int insertleaveRecord(LeaveRecord leaveRecord) {
		return leaveRecordDaoImpl.insertleaveRecord(leaveRecord);
	}

	@Override
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo) {
		return leaveRecordDaoImpl.selectLeaveRecordByStudent(studentRoNo);
	}

	@Override
	public int updateLeaveRecordByStudent(int leaveRecordId,String status) {
		return leaveRecordDaoImpl.updateLeaveRecordByStudent(leaveRecordId, status);
	}

	@Override
	public LeaveRecord selectLeaveRecordByleaveRecordId(int leaveRecordId) {
		return leaveRecordDaoImpl.selectLeaveRecordByleaveRecordId(leaveRecordId);
	}

	@Override
	public LeaveRecord selectLeaveRecordByStudentLimit(String studentRoNo) {
		return leaveRecordDaoImpl.selectLeaveRecordByStudentLimit(studentRoNo);
	}

	@Override
	public List<LeaveRecord> selectLeaveRecordByStudentAndCourse(String studentRoNo, int courseId, String status) {
		return leaveRecordDaoImpl.selectLeaveRecordByStudentAndCourse(studentRoNo, courseId, status);
	}
	
	

}
