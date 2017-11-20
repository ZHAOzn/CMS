package com.qdu.service;

import java.util.List;

import com.qdu.pojo.LeaveRecord;

public interface LeaveRecordService {
	
	public int insertleaveRecord(LeaveRecord leaveRecord);
	
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo);
   
}
