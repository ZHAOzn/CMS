package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.pojo.MyBlog;
import com.qdu.service.ClazzService;
import com.qdu.service.ClazzStuService;
import com.qdu.service.CourseService;
import com.qdu.service.LeaveRecordService;
import com.qdu.service.LogEntityService;
import com.qdu.service.MessageService;
import com.qdu.service.MyBlogService;
import com.qdu.service.QrTemService;
import com.qdu.service.StudentInfoService;
import com.qdu.service.StudentService;
import com.qdu.service.TeacherService;

@Controller
@RequestMapping(value = "/blog")
public class MyBlogController {

	@Autowired
	private StudentService studentServiceImpl;
	@Autowired
	private ClazzService clazzServiceImpl;
	@Autowired
	private QrTemService qrTemServiceImpl;
	@Autowired
	private CourseService courseServiceImpl;
	@Autowired
	private StudentInfoService studentInfoServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@Autowired
	private TeacherService teacherServiceImpl;
	@Autowired 
	private LogEntityService logEntityServiceImpl;
	@Autowired 
	private ClazzStuService clazzStuServiceImpl;
	@Autowired
	private LeaveRecordService leaveRecordServiceImpl;
	@Autowired 
	private MyBlogService myBlogServiceImpl;
	
	//新建博文
	@RequestMapping(value = "/insertMyBlog.do")
	@ResponseBody
	public Map<String, Object> insertMyBlog(String blogAuthor,String role,String belongTo,String blogContent,
			String blogTarget,String blogFifter,String title){
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		MyBlog myBlog = new MyBlog();
		myBlog.setBlogTitle(title);
		myBlog.setBlogAuthor(blogAuthor);
		myBlog.setRole(role);
		myBlog.setBelongTo(belongTo);
		myBlog.setBlogContent(blogContent);
		myBlog.setBlogFifter(blogFifter);
		myBlog.setBlogTarget(blogTarget);
		myBlog.setCreateTime(sdf.format(new Date()));
		myBlog.setUp(0);
		myBlog.setDown(0);
		myBlog.setHotClick(0);
		myBlog.setBlogComment(0);
		int tem = myBlogServiceImpl.insertMyBlog(myBlog);
		if(tem > 0){
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		return map;
	}
	//得到一个随机数用于排除恶意机器人
	@RequestMapping(value = "/getRandomValue.do")
	@ResponseBody
	public Map<String, Object> getRandomValue(){
		Map<String, Object> map = new HashMap<>();
		int a1 = new Random().nextInt(90) + 10;
		int a2 = new Random().nextInt(90) + 10;
		int sum = a1 + a2;
		map.put("a1", a1); 
		map.put("a2", a2);
		map.put("sum", sum);
		return map;
	}
	
	
	
	
	
}
