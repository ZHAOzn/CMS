package com.qdu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.aop.SystemLog;
import com.qdu.cache.RedisCache;
import com.qdu.pojo.Admin;
import com.qdu.pojo.LogEntity;
import com.qdu.pojo.Student;
import com.qdu.pojo.Teacher;
import com.qdu.service.LogEntityService;
import com.qdu.service.StudentService;
import com.qdu.service.TeacherService;
import com.qdu.util.Page;
//超级管理员+相关操作
@Controller
@RequestMapping(value="/admin")
public class LogEntityController {

	@Autowired LogEntityService logEntityServiceImpl;
	@Autowired StudentService studentServiceImpl;
	@Autowired TeacherService teacherServiceImpl;
	//超管准备登录
	@RequestMapping(value="/forAdminLogin.do")
	public String forAdminLogin(ModelMap map) {
		return "adminPage";
	} 
	
	//超级管理员登录准备
	@RequestMapping(value = "/ConfirmAdminCount.do")
	@ResponseBody
	public Map<String, Object> adminLogin(String adminId,String adminPassword, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		Admin admin = logEntityServiceImpl.selectAdminById(adminId);
		if(admin != null && adminPassword.equals(admin.getAdminPassword())){
			map.put("result", true);
		}
		else {
			map.put("result", false); 
		}
		return map;
	}
	//管理员登录
	@RequestMapping(value = "/adminLogin.do")
	public String adminLogin(ModelMap map,HttpServletRequest request){
		List<LogEntity> logEntities = new ArrayList<LogEntity>();
		 int totalCount = logEntityServiceImpl.selectLogEntityCount();
		    List<Student> students = studentServiceImpl.selectStuList();
		    List<Teacher> teachers = teacherServiceImpl.selectTeacher();
			map.put("logEntities", logEntities);
			map.put("students", students);
			map.put("teachers", teachers);
		return "superManagerPage";
	}
	//超级管理员登录准备
		@RequestMapping(value = "/clearRedis.do")
		@ResponseBody
		public Map<String, Object> clearRedis(){
			Map<String, Object> map = new HashMap<>();
			 new RedisCache("com.qdu.mapping.AdminMapping").clear();
	    	 new RedisCache("com.qdu.mapping.ClazzMapping").clear();
	    	 new RedisCache("com.qdu.mapping.ClazzStuMapping").clear();
	    	 new RedisCache("com.qdu.mapping.CourseMapping").clear();
	    	 new RedisCache("com.qdu.mapping.ExaminationMapping").clear();
	    	 new RedisCache("com.qdu.mapping.FilePackageMapping").clear();
	    	 new RedisCache("com.qdu.mapping.LeaveRecordMapping").clear();
	    	 new RedisCache("com.qdu.mapping.LogEntityMapping").clear();
	    	 new RedisCache("com.qdu.mapping.MessageMapping").clear();
	    	 new RedisCache("com.qdu.mapping.PackMapping").clear();
	    	 new RedisCache("com.qdu.mapping.QrTemMapping").clear();
	    	 new RedisCache("com.qdu.mapping.ShortAnswerMapping").clear();
	    	 new RedisCache("com.qdu.mapping.SingleSelectionMapping").clear();
	    	 new RedisCache("com.qdu.mapping.StudentInfoMapping").clear();
	    	 new RedisCache("com.qdu.mapping.StudentMapping").clear();
	    	 new RedisCache("com.qdu.mapping.TeacherMapping").clear();
	    	 new RedisCache("com.qdu.mapping.TestMapping").clear();
			map.put("result", true);
			return map;
		}
	
}
