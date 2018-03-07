package com.qdu.pojo;

public class StudentInfoDetail {
	private int StudentInfoDetailId;
	private String StudentRoNo;
	private int CourseId;
	private String CurrentTime;
	private String CurrentWeek;
	private int CurrentCount;
	private String SignInStatus;
	
	
	
	public String getCurrentWeek() {
		return CurrentWeek;
	}
	public void setCurrentWeek(String currentWeek) {
		CurrentWeek = currentWeek;
	}
	public int getCurrentCount() {
		return CurrentCount;
	}
	public void setCurrentCount(int currentCount) {
		CurrentCount = currentCount;
	}
	public int getStudentInfoDetailId() {
		return StudentInfoDetailId;
	}
	public void setStudentInfoDetailId(int studentInfoDetailId) {
		StudentInfoDetailId = studentInfoDetailId;
	}
	public String getStudentRoNo() {
		return StudentRoNo;
	}
	public void setStudentRoNo(String studentRoNo) {
		StudentRoNo = studentRoNo;
	}
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public String getCurrentTime() {
		return CurrentTime;
	}
	public void setCurrentTime(String currentTime) {
		CurrentTime = currentTime;
	}
	public String getSignInStatus() {
		return SignInStatus;
	}
	public void setSignInStatus(String signInStatus) {
		SignInStatus = signInStatus;
	}

	
}
