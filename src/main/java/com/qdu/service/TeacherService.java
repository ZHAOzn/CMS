package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Teacher;

public interface TeacherService { 
	
public void insertTeacher(Teacher teacher);
		
	public Teacher selectTeacherByEmail(String teacherMobile);
	
	public void updateTeacherPassWord(String teacherMobile,String teacherPassword);
	 
	public Teacher selecctTeacherByThree(String teacherMobile, String teacherName, String teacherEmail);
	
	public void changeTeaMail(String teacherMobile, String teacherEmail);
	
	public Teacher selectTeacherByMobile(String teacherMobile);
 
	public int updateStudentextra(Teacher teacher);
	
	public Teacher selectTeacherNameByMobile(String teacherMobile);
	
	public List<Teacher> selectTeacher();
	
	public Teacher selectTeacherDetail(String teacherMobile);
}
