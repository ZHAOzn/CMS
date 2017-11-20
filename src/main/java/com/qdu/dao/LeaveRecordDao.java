package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.LeaveRecord;

public interface LeaveRecordDao {
	
	public int insertleaveRecord(LeaveRecord leaveRecord);
	
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo);

}
