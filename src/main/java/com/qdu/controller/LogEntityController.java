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
import com.qdu.pojo.Admin;
import com.qdu.pojo.LogEntity;
import com.qdu.pojo.Student;
import com.qdu.service.LogEntityService;
import com.qdu.service.StudentService;
import com.qdu.util.Page;
//超级管理员+相关操作
@Controller
@RequestMapping(value="/admin")
public class LogEntityController {

	@Autowired LogEntityService logEntityServiceImpl;
	@Autowired StudentService studentServiceImpl;
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
			map.put("logEntities", logEntities);
			map.put("students", students);
		return "superManagerPage";
	}
	
}
