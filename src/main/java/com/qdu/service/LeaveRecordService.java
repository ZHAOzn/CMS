package com.qdu.service;

import java.util.List;

import com.qdu.pojo.LeaveRecord;

public interface LeaveRecordService {
	
	public int insertleaveRecord(LeaveRecord leaveRecord);
	
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo);
	
	public int updateLeaveRecordByStudent(int leaveRecordId,String status);
	
	public LeaveRecord selectLeaveRecordByleaveRecordId(int leaveRecordId);
	
	public LeaveRecord selectLeaveRecordByStudentLimit(String studentRoNo);
	
	public List<LeaveRecord> selectLeaveRecordByStudentAndCourse(String studentRoNo, int courseId, String status);
   
}
