package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.aop.SystemLog;
import com.qdu.pojo.Clazz;
import com.qdu.pojo.ClazzStu;
import com.qdu.pojo.Course;
import com.qdu.pojo.StudentInfo;
import com.qdu.pojo.Teacher;
import com.qdu.service.ClazzService;
import com.qdu.service.ClazzStuService;
import com.qdu.service.CourseService;
import com.qdu.service.StudentInfoService;

@Controller
@RequestMapping(value = "/clazz") 
public class ClazzController {
 
	@Autowired private ClazzService clazzServiceImpl;
	@Autowired private CourseService courseServiceImpl;
	@Autowired private ClazzStuService clazzStuServiceImpl;
	@Autowired private StudentInfoService studentInfoServiceImpl;
	//添加班级准备
	@RequestMapping(value = "/forInsertClazz.do")
	public String forInsertClazz(int courseId,ModelMap map){
		return "clazzInfo";
	}
	//添加班级
	@SystemLog(module="教师",methods="日志管理-添加班级")
	@RequestMapping(value = "/insertClazz.do")
	public String insertClazz(ModelMap map,Clazz clazz){
		clazzServiceImpl.insertClazz(clazz);
		return "clazzInfo";
	}
	//添加课程准备
	@RequestMapping(value = "/forAddClazz.do")
	public String forAddClazz(int courseId,ModelMap map){
		Course course = courseServiceImpl.selectCourseById(courseId);
		map.put("course", course);
		return "addClazz";
	}
	//添加课程
	@SystemLog(module="教师",methods="日志管理-添加课程")
	@RequestMapping(value = "/addClazz.do")
	@ResponseBody
	public Map<String, Object> addClazz(String clazzName,int courseId,
			@DateTimeFormat(pattern = "yyyy") Date currentYear,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		System.out.println(courseId);
		Clazz clazz = new Clazz();
		clazz.setClazzName(clazzName);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");   
		clazz.setCurrentYear(Integer.parseInt(sdf.format(currentYear)));
		clazz.setCourse(courseServiceImpl.selectCourseById(courseId));
		int tem = clazzServiceImpl.insertClazz(clazz);
		if(tem > 0){
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		
		return map;
	}
	//修改班级信息
	@SystemLog(module="教师",methods="日志管理-修改班级")
	@RequestMapping(value="/changeClazzByAjax.do")
	public @ResponseBody Map<String, Object>
	changeClazzByAjax(int clazzId,String clazzName,@DateTimeFormat(pattern = "yyyy") Date currentYear){
		Clazz clazz = clazzServiceImpl.selectClazzById(clazzId);
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");   
		if(clazz != null){
			clazzServiceImpl.updateClazzByClazzId(clazzId, clazzName,Integer.parseInt(sdf.format(currentYear)));
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		return map;
	}
	
	//删除Clazz及旗下的student
	@SystemLog(module="教师",methods="日志管理-删除班级")
	@RequestMapping(value = "/deleteClazzById.do")
	public @ResponseBody Map<String, Object> deleteClazzById(int clazzId){
		int tem = clazzStuServiceImpl.deleteClazzStuByClazzId(clazzId);
		Clazz clazz = clazzServiceImpl.selectClazzById(clazzId);
		studentInfoServiceImpl.deleteStudentInfoByCourse(clazz.getCourse().getCourseId());
		int tem2 = clazzServiceImpl.deleteClazzById(clazzId);
		Map<String, Object> map = new HashMap<>();
		map.put("message", "删除成功");
		return map;
	}
	
}
