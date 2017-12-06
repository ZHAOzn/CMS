package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Student;
import com.qdu.pojo.StudentInfo;

public interface StudentInfoService { 

	public int insertStudentInfo(StudentInfo studentInfo);
	
	public void deleteStudentInfoByCourse(int courseId);
	 
	public StudentInfo selectStudentInfoByMany(String studentRoNo, int courseId);
	
	public void updateStudentInfoAboutSignIn(int studentInfoId, int signIn);
	
	public List<StudentInfo> selectInfoList(int courseId);
	
	public void updateStudentInfoAboutAbs(int studentInfoId, int absenteeism);
	
	public List<StudentInfo> selectInfoFromInfoAndStudent(int courseId);
	
	public List<StudentInfo> selectCourseByStudentRono(String studentRoNo);
	
	public List<StudentInfo> selectStudentInfoList(String studentRoNo);
	
	public int updateStudentInfoAboutLeave(int studentInfoId, int askForLeave);
	
	public int selectCountOfStudentByStudentInfo(int courseId);
	
	public StudentInfo selectStudentInfoOfLate(String studentRoNo, int courseId);
	
	public int updateStudentInfoAboutLateOrLeave(int comeLate, int leaveEarlier, int studentInfoId);
	
	public StudentInfo selectStudentInfoById(int studentInfoId);
	
}
