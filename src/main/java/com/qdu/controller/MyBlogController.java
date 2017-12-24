package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.pojo.MyBlog;
import com.qdu.pojo.MyBlogUp;
import com.qdu.pojo.Student;
import com.qdu.pojo.Teacher;
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
	@RequestMapping(value = "/insertMyBlog.do",method = RequestMethod.POST)
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
		myBlog.setVerify("等待审核"); 
		int tem = myBlogServiceImpl.insertMyBlog(myBlog);
		if(tem > 0){
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		return map;
	}
	//发表博文时得到一个随机数用于排除恶意机器人
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
	//教师获取某一博文
	@RequestMapping(value = "/getBlogById.do")
	@ResponseBody
	public Map<String, Object> getBlogById(int blogId){
		Map<String, Object> map = new HashMap<>();
		MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
		if(myBlog.getRole().equals("教师")){
			Teacher teacher = teacherServiceImpl.selectTeacherNameByMobile(myBlog.getBlogAuthor());
			if(myBlog != null){
				map.put("result", true);
				map.put("teacher", teacher);
				map.put("userName", teacher.getTeacherName());
				map.put("myBlog", myBlog);
			}else{
				map.put("result", false);
			}
		}else {
			Student student = studentServiceImpl.selectStudentByNo(myBlog.getBlogAuthor());
			if(myBlog != null){
				map.put("result", true);
				map.put("student", student);
				map.put("userName", student.getStudentName());
				map.put("myBlog", myBlog);
			}else{
				map.put("result", false);
			}
		}
		
		return map;
	}
	
	//学生获取某一博文
		@RequestMapping(value = "/getStudentBlogById.do")
		@ResponseBody
		public Map<String, Object> getStudentBlogById(int blogId){
			Map<String, Object> map = new HashMap<>();
			MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
			Student student = studentServiceImpl.selectStudentByNo(myBlog.getBlogAuthor());
			if(myBlog != null){
				map.put("result", true);
				map.put("student", student);
				map.put("myBlog", myBlog);
			}else{
				map.put("result", false);
			}
			
			return map;
		}
	
	//查看博文
	@RequestMapping(value = "/teacherReadBlog.do")
	@ResponseBody
	public Map<String, Object> teacherReadBlog(String blogAuthor){
		System.out.println(blogAuthor);
		Map<String, Object> map = new HashMap<>();
		List<MyBlog> myBlogs = myBlogServiceImpl.selectMyBlogByUserId(blogAuthor);
		map.put("myBlogs", myBlogs);
		return map;
	}
	//查看博文通过热度
	@RequestMapping(value = "/teacherReadBlogByHot.do")
	@ResponseBody
	public Map<String, Object> teacherReadBlogByHot(String blogAuthor){
		Map<String, Object> map = new HashMap<>();
		List<MyBlog> myBlogs = myBlogServiceImpl.selectMyBlogByHot(blogAuthor);
		map.put("myBlogs", myBlogs);
		return map;
	}
	//删除博文
		@RequestMapping(value = "/deleteMyBlog.do")
		@ResponseBody
		public Map<String, Object> deleteMyBlog(int blogId){
			Map<String, Object> map = new HashMap<>();
			int tem = myBlogServiceImpl.deleteMyBlogById(blogId);
			if(tem > 0){
				map.put("result", true);
			}else {
				map.put("result", false);
			}
			return map;
		}
        //修改博文
		@RequestMapping(value = "/updateMyBlog.do",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> updateMyBlog(int blogId,String blogAuthor,String role,String belongTo,String blogContent,
				String blogTarget,String blogFifter,String title){
			Map<String, Object> map = new HashMap<>();
			MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
			myBlog.setBlogTitle(title);
			myBlog.setBelongTo(belongTo);
			myBlog.setBlogContent(blogContent);
			myBlog.setBlogFifter(blogFifter);
			myBlog.setBlogTarget(blogTarget);
			int tem = myBlogServiceImpl.updateBlog(myBlog); 
			if(tem > 0){
				map.put("result", true);
			}else {
				map.put("result", false);
			}
			return map;
		}
	//教师查看资料时选择查看博文
	@RequestMapping(value = "/teacherSearchBlog.do")
	@ResponseBody
	public Map<String, Object> teacherSearchBlog(String fileName){
		Map<String, Object> map = new HashMap<>();
		List<MyBlog> myBlogs  = myBlogServiceImpl.selectMyBlogByConCat(fileName); 
		map.put("result", true);
		map.put("myBlogs", myBlogs);
		return map;
	}
		//查看博文
	@RequestMapping(value = "/getBlogTemValue.do")
	public String getBlogTemValue(int blogId,String role,String userId,ModelMap map){
		MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
		if(myBlog.getRole().equals("教师")){
			map.put("name", teacherServiceImpl.selectTeacherByMobile(myBlog.getBlogAuthor()).getTeacherName());
			map.put("myBlog", myBlog);
			map.put("userId", userId);
			map.put("role", role);
			if(!userId.equals(myBlog.getBlogAuthor())){
				myBlog.setHotClick(myBlog.getHotClick()+1);
				myBlogServiceImpl.updateBlogofHotClick(myBlog);
			}
		
		}else {
			map.put("name", studentServiceImpl.selectStudentByNo(myBlog.getBlogAuthor()).getStudentName());
			map.put("myBlog", myBlog);
			map.put("userId", userId);
			map.put("role", role);
			if(!userId.equals(myBlog.getBlogAuthor())){
				myBlog.setHotClick(myBlog.getHotClick()+1);
				myBlogServiceImpl.updateBlogofHotClick(myBlog);
			}
		}
		return "blogDetail";
	}
	//学生查看博文
	@RequestMapping(value = "/studentSearchBlog.do")
	@ResponseBody
	public Map<String, Object> studentSearchBlog(String blogContent,String belongTo){
		Map<String, Object> map = new HashMap<>();
		List<MyBlog> myBlogs  = myBlogServiceImpl.studentSearchBlog(blogContent, belongTo);
		map.put("result", true);
		map.put("myBlogs", myBlogs);
		return map;
	}
	//点赞
	@RequestMapping(value = "/upMyBlog.do")
	@ResponseBody
	public Map<String, Object> upMyBlog(String userId,String role,int blogId){
		Map<String, Object> map = new HashMap<>();
		MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
		MyBlogUp myBlogUp = myBlogServiceImpl.selectMyBlogUp(userId, blogId);
		int tem = 0;
		if(myBlogUp != null){
			tem = myBlogServiceImpl.deleteMyBlogUp(myBlogUp.getMyBlogUpId());
			myBlog.setUp(myBlog.getUp()-1);
			myBlogServiceImpl.updateBlogofUp(myBlog);
		}else {
			MyBlogUp myBlogUp2 = new MyBlogUp();
			myBlogUp2.setBlogId(blogId);
			myBlogUp2.setUserId(userId);
			tem = myBlogServiceImpl.insertMyBlogUp(myBlogUp2);
			myBlog.setUp(myBlog.getUp()+1);
			myBlogServiceImpl.updateBlogofUp(myBlog);
		}
		MyBlog myBlog2 = myBlogServiceImpl.selectMyBlogById(blogId);
		if(tem > 0){
			map.put("result", true);
			map.put("myBlog2", myBlog2);
		}else {
			map.put("result", false);
			map.put("myBlog2", myBlog2);
		}
		return map;
	}
	//垃圾文章，中指
	@RequestMapping(value = "/downMyBlog.do")
	@ResponseBody
	public Map<String, Object> downMyBlog(String userId,String role,int blogId){
		Map<String, Object> map = new HashMap<>();
		MyBlog myBlog = myBlogServiceImpl.selectMyBlogById(blogId);
		MyBlogUp myBlogUp = myBlogServiceImpl.selectMyBlogUp(userId, blogId);
		int tem = 0;
		if(myBlogUp != null){
			tem = myBlogServiceImpl.deleteMyBlogUp(myBlogUp.getMyBlogUpId());
			myBlog.setUp(myBlog.getUp()-1);
			myBlogServiceImpl.updateBlogofUp(myBlog);
			myBlog.setDown(myBlog.getDown()+1);
			myBlogServiceImpl.updateBlogofDown(myBlog);
		}else {
			MyBlogUp myBlogUp2 = new MyBlogUp();
			myBlogUp2.setBlogId(blogId);
			myBlogUp2.setUserId(userId);
			tem = myBlogServiceImpl.insertMyBlogUp(myBlogUp2);
			myBlog.setDown(myBlog.getDown()+1);
			myBlogServiceImpl.updateBlogofUp(myBlog);
		}
		MyBlog myBlog2 = myBlogServiceImpl.selectMyBlogById(blogId);
		if(tem > 0){
			map.put("result", true);
			map.put("myBlog2", myBlog2);
		}else {
			map.put("result", false);
			map.put("myBlog2", myBlog2);
		}
		return map;
	}
	
	
	
	
	
	
	
	
}
