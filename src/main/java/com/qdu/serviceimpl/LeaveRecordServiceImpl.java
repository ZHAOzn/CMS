package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.LeaveRecordDao;
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
	
	

}
