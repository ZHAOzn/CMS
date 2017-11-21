package com.qdu.pojo;

import java.io.Serializable;

public class LeaveRecord implements Serializable{
	private int leaveRecordId;
	private String student;
	private int courseId;
	private String courseName;
	private String reason;
	private String leaveTime;
	private String returnTime;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLeaveRecordId() {
		return leaveRecordId;
	}
	public void setLeaveRecordId(int leaveRecordId) {
		this.leaveRecordId = leaveRecordId;
	}
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	

}
