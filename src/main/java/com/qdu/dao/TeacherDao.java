package com.qdu.dao;


import java.util.List;

import com.qdu.pojo.Teacher;

public interface TeacherDao {
	
    public Teacher selectTeacherByEmail(String teacherMobile);
	
	public void insertTeacher(Teacher teacher);
	
	public void updateTeacherPassWord(String teacherMobile,String teacherPassword);
	
	public Teacher selecctTeacherByThree(String teacherMobile,String teacherName,String teacherEmail);
	
	public void changeTeaMail(String teacherMobile,String teacherEmail);
	
	public Teacher selectTeacherByMobile(String teacherMobile);
	
	public int updateStudentextra(Teacher teacher);
	
	public Teacher selectTeacherNameByMobile(String teacherMobile);
	
	public List<Teacher> selectTeacher();
	
	public Teacher selectTeacherDetail(String teacherMobile);
 
}
