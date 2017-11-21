package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.LeaveRecord;

public interface LeaveRecordDao {
	
	public int insertleaveRecord(LeaveRecord leaveRecord);
	
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo);
	
	public int updateLeaveRecordByStudent(int leaveRecordId,String status);
	
	public LeaveRecord selectLeaveRecordByleaveRecordId(int leaveRecordId);
	
	public LeaveRecord selectLeaveRecordByStudentLimit(String studentRoNo);

}
