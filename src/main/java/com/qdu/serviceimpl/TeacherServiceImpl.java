package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.TeacherDao;
import com.qdu.pojo.Teacher;
import com.qdu.service.TeacherService;

@Transactional 
@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao teacherDaoImpl;
	  
	@Override
	public void insertTeacher(Teacher teacher) {
		teacherDaoImpl.insertTeacher(teacher);
	}
 

	@Override
	public Teacher selectTeacherByEmail(String teacherMobile) {
		return teacherDaoImpl.selectTeacherByEmail(teacherMobile);
	}


	@Override
	public void updateTeacherPassWord(String teacherMobile,String teacherPassword) {
		teacherDaoImpl.updateTeacherPassWord(teacherMobile, teacherPassword);
	}


	@Override
	public Teacher selecctTeacherByThree(String teacherMobile, String teacherName, String teacherEmail) {
		return teacherDaoImpl.selecctTeacherByThree(teacherMobile, teacherName, teacherEmail);
	}


	@Override
	public void changeTeaMail(String teacherMobile, String teacherEmail) {
		teacherDaoImpl.changeTeaMail(teacherMobile, teacherEmail);
	}


	@Override
	public Teacher selectTeacherByMobile(String teacherMobile) {
		return teacherDaoImpl.selectTeacherByMobile(teacherMobile);
	}


	@Override
	public int updateStudentextra(Teacher teacher) {
		return teacherDaoImpl.updateStudentextra(teacher);
	}


	@Override
	public Teacher selectTeacherNameByMobile(String teacherMobile) {
		return teacherDaoImpl.selectTeacherNameByMobile(teacherMobile);
	}


	@Override
	public List<Teacher> selectTeacher() {
		return teacherDaoImpl.selectTeacher();
	}

}
