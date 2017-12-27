package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.aop.SystemLog;
import com.qdu.pojo.Clazz;
import com.qdu.pojo.ClazzStu;
import com.qdu.pojo.Course;
import com.qdu.pojo.Message;
import com.qdu.pojo.Student;
import com.qdu.pojo.StudentInfo;
import com.qdu.pojo.Teacher;
import com.qdu.service.ClazzService;
import com.qdu.service.ClazzStuService;
import com.qdu.service.CourseService;
import com.qdu.service.MessageService;
import com.qdu.service.StudentInfoService;
import com.qdu.service.StudentService;
import com.qdu.service.TeacherService;
import com.qdu.serviceimpl.ClazzServiceImpl;
import com.qdu.util.MD5Util;

@Controller 
@RequestMapping(value = "/studentInfo")
public class StudentInfoController {

	@Autowired
	StudentInfoService studentInfoServiceImpl;
	@Autowired
	StudentService studentServiceImpl;
	@Autowired
	private TeacherService teacherServiceImpl;
	@Autowired 
	private MessageService messageServiceImpl;
	@Autowired
	private CourseService courseServiceImpl;
	@Autowired
	private ClazzStuService clazzStuServiceImpl;
	@Autowired ClazzService clazzServiceImpl;

	// 添加学生——课程 中间表
	@SystemLog(module = "中间表", methods = "日志管理-添加中间表")
	@RequestMapping(value = "/insertStudentInfo.do")
	public String insertStudentInfo(StudentInfo studentInfo, HttpServletRequest request) {
		String studentRoNo = request.getParameter("studentRoNo");
		String password = request.getParameter("studentPassword");
		String courseId = request.getParameter("courseId");
		Course course = courseServiceImpl.selectCourseById(Integer.parseInt(courseId));
		int count = studentInfoServiceImpl.selectCountOfStudentByStudentInfo(Integer.parseInt(courseId));
		Student student = studentServiceImpl.selectStudentByNo(studentRoNo);
		if(count < course.getClassCapacity()){
			if (student != null && MD5Util.md5(password, "juin").equals(student.getStudentPassword())) {
				StudentInfo studentInfo2 = studentInfoServiceImpl.selectStudentInfoByMany(studentRoNo,
						Integer.parseInt(courseId));
				if (studentInfo2 == null) {
					StudentInfo studentInfo3 = new StudentInfo();
					studentInfo3.setStudent(student);
					studentInfo3.setCourse(course);
					studentInfo3.setSignIn(0);
					studentInfo3.setAbsenteeism(0);
					studentInfo3.setAskForLeave(0);
					studentInfo3.setComeLate(0);
					studentInfo3.setLeaveEarlier(0);
					studentInfo3.setLeaveRecord(0);
					studentInfoServiceImpl.insertStudentInfo(studentInfo3);
				}
				int clazzId = Integer.parseInt(request.getParameter("clazzId"));
				if (clazzId != 0) {
					clazzStuServiceImpl.insertClazzStu(clazzId,studentRoNo);
				}
				return "success";
			}
			else {
				return "failer";
			}
		} else {
			return "failer";
		}

	}

	// 添加学生——课程 中间表 
	@SystemLog(module = "教师", methods = "日志管理-添加学生")
	@RequestMapping(value = "/insertStudentInfoByteacher.do")
	@ResponseBody
	public Map<String, Object> insertStudentInfoByteacher(String content,String teacherMobile, String studentRoNo,
			HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int tem = 0;
        for(int i = 0; i < content.length(); i ++){
        	if(content.charAt(i) == 'c'){
        		tem = i;
        		break;
        	}
        }
        String aa = content.substring(0, tem);
        String bb = content.substring(tem+1);
        int courseId = Integer.parseInt(aa);
        int clazzId = Integer.parseInt(bb);
		StudentInfo studentInfo2 = studentInfoServiceImpl.selectStudentInfoByMany(studentRoNo,courseId);
		if (studentInfo2 == null) {
			if (clazzId != 0) {
				int tem2 = clazzStuServiceImpl.insertClazzStu(clazzId,studentRoNo);
				System.out.println(tem2);
				if(tem2 > 0){
					Student student = studentServiceImpl.selectStudentByNo(studentRoNo);
					Course course = courseServiceImpl.selectCourseById(courseId);
					StudentInfo studentInfo3 = new StudentInfo();
					studentInfo3.setStudent(student);
					studentInfo3.setCourse(course);
					studentInfo3.setSignIn(0);
					studentInfo3.setAbsenteeism(0);
					studentInfo3.setAskForLeave(0);
					studentInfo3.setComeLate(0);
					studentInfo3.setLeaveEarlier(0);
					studentInfo3.setLeaveRecord(0);
					studentInfoServiceImpl.insertStudentInfo(studentInfo3);
				}
			}
			Teacher teacher = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
	        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
	        Message message =  new Message();
	        Course course = courseServiceImpl.selectCourseById(courseId);
			 message.setMessageSender(teacherMobile);
			 message.setMessageAccepter(studentRoNo);
			 message.setMessageTitle(teacher.getTeacherName() + "老师同意你加入课程< " + course.getCourseName() + 
					 ">(" + course.getCurrentYear() + "/" + course.getSchoolTem() + ")");
			 message.setSendTime(time);
			 message.setHaveRead("未读"); 
			 message.setMessageType("insertCourse");
			 message.setMessageContent(courseId+"");
			 messageServiceImpl.insertMessage(message);
			map.put("result", true);
		}else{
			map.put("result", false);
		} 
		return map;
	}
	//修改学生迟到早退的情况
	@RequestMapping(value = "/changeQiandao.do")
	@ResponseBody 
	public Map<String, Object> changeQiandao(String studentRoNo,int courseId){
		Map<String, Object> map = new HashMap<>();
		StudentInfo studentInfo = studentInfoServiceImpl.selectStudentInfoOfLate(studentRoNo, courseId);		
		Student student = studentServiceImpl.selectStudentByNo(studentRoNo);
		map.put("studentInfo", studentInfo);
		map.put("student", student);
		return map; 
	}
	//添加学生迟到次数
	@RequestMapping(value = "/addComeLate.do")
	@ResponseBody
	public Map<String, Object> addComeLate(int studentInfoId){
		Map<String, Object> map = new HashMap<>();
		StudentInfo studentInfo = studentInfoServiceImpl.selectStudentInfoById(studentInfoId);
		int tem = studentInfoServiceImpl.updateStudentInfoAboutLateOrLeave(studentInfo.getComeLate()+1, studentInfo.getLeaveEarlier(), studentInfoId);
		if(tem > 0){
			map.put("result", true);
			StudentInfo studentInfo2 = studentInfoServiceImpl.selectStudentInfoById(studentInfoId);
			map.put("studentInfo", studentInfo2);
		}else {
			map.put("result", false);
		}
		return map;
	}
	
	//添加早退迟到次数
		@RequestMapping(value = "/addLeaveEarly.do")
		@ResponseBody
		public Map<String, Object> addLeaveEarly(int studentInfoId){
			Map<String, Object> map = new HashMap<>();
			StudentInfo studentInfo = studentInfoServiceImpl.selectStudentInfoById(studentInfoId);
			int tem = studentInfoServiceImpl.updateStudentInfoAboutLateOrLeave(studentInfo.getComeLate(), studentInfo.getLeaveEarlier()+1, studentInfoId);
			if(tem > 0){
				map.put("result", true);
				StudentInfo studentInfo2 = studentInfoServiceImpl.selectStudentInfoById(studentInfoId);
				map.put("studentInfo", studentInfo2);
			}else {
				map.put("result", false);
			}
			return map;
		}


}
