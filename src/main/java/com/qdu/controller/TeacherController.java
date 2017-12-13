package com.qdu.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdu.aop.SystemLog;
import com.qdu.pojo.ClazzStu;
import com.qdu.pojo.Course;
import com.qdu.pojo.Feedback;
import com.qdu.pojo.FilePackage;
import com.qdu.pojo.LeaveRecord;
import com.qdu.pojo.LogEntity;
import com.qdu.pojo.Message;
import com.qdu.pojo.MyBlog;
import com.qdu.pojo.Student;
import com.qdu.pojo.StudentInfo;
import com.qdu.pojo.Teacher;
import com.qdu.service.ClazzService;
import com.qdu.service.ClazzStuService;
import com.qdu.service.CourseService;
import com.qdu.service.FilePackageService;
import com.qdu.service.LeaveRecordService;
import com.qdu.service.LogEntityService;
import com.qdu.service.MessageService;
import com.qdu.service.MyBlogService;
import com.qdu.service.StudentInfoService;
import com.qdu.service.StudentService;
import com.qdu.service.TeacherService;
import com.qdu.serviceimpl.StudentServiceImpl;
import com.qdu.util.JavaEmailSender;
import com.qdu.util.MD5Util;
import com.qdu.util.Page;
import com.qdu.util.ResponseUtil;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	// 注解注入服务，应用 Spring IOC功能
	@Autowired
	private TeacherService teacherServiceImpl;
	@Autowired
	private CourseService courseServiceImpl;
	@Autowired
	private ClazzService clazzServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@Autowired
	private StudentService studentServiceImpl;
	@Autowired
	private FilePackageService filePackageServiceImpl;
	@Autowired
	private LogEntityService logEntityServiceImpl;
	@Autowired
	private LeaveRecordService leaveRecordServiceImpl;
	@Autowired
	private StudentInfoService studentInfoServiceImpl;
	@Autowired 
	private MyBlogService myBlogServiceImpl;
	@Autowired 
	private ClazzStuService clazzStuServiceImpl;

	// 教师登录准备
	@RequestMapping(value = "/forTeacherLogin.do")
	public String forTeacherLogin() {
		return "teacherLogin";
	}

	// 教师注册准备
	@RequestMapping(value = "/forTeacherRegister.do")
	public String forTeacherRegister() {
		return "addTeacher";
	}

	// 教师登录
	@SystemLog(module = "教师", methods = "日志管理-登录/刷新")
	@RequestMapping(value = "/teacherLogin.do",method = RequestMethod.POST)
	public String teacherLogin(HttpServletRequest request,String id, String password, ModelMap map) {
		if (id == null) {
			id = request.getParameter("teacherId");
		}
		if (password == null) {
			id = password = request.getParameter("password");
		}
		Teacher teacher = teacherServiceImpl.selectTeacherByEmail(id);
		if (teacher != null) {
			if (id.equals(teacher.getTeacherMobile())) {
				if (MD5Util.md5(password, "teacher").equals(teacher.getTeacherPassword())
						|| password.equals(teacher.getTeacherPassword())) {
					map.addAttribute("teacher", teacher);
					List<Course> courses = courseServiceImpl.selectCourseByTeacher(teacher.getTeacherMobile());
					map.addAttribute("courses", courses);
					int messageCount = messageServiceImpl.selectMessageCount(teacher.getTeacherMobile());
					map.put("messageCount", messageCount);
					List<LogEntity> logEntities = logEntityServiceImpl.selectStudentLog(id);
					map.put("logEntity", logEntities);
					// session的id存一下
					request.getSession().setAttribute("UserId", id);
					return "teacherPage";
				} else
					return "failer";
			}
		}
		return "failer";
	}

	// 教师注销登录
	@SystemLog(module = "教师", methods = "日志管理-注销")
	@RequestMapping(value = "/exitLogin.do")
	@ResponseBody
	public void exitLogin(String teacherMobile, HttpServletRequest request) {
		// session的id存一下
		request.getSession().setAttribute("UserId", teacherMobile);
		// 数据库记录注销
	}

	// 教师头一次登录
	@SystemLog(module = "教师", methods = "日志管理-跳转首页")
	@RequestMapping(value = "/teacherFirstLogin.do")
	public String teacherFirstLogin(HttpServletRequest request, String pageNow, ModelMap map, String teacherMobile) {
		Teacher teacher = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
		Page page = null;
		String repageNow = request.getParameter("repageNow");
		if (repageNow != null) {
			pageNow = repageNow;
		} else {
			pageNow = 1 + "";
		}
		int totalCount = messageServiceImpl.selectMessageTotalCount(teacher.getTeacherMobile());
		page = new Page(totalCount, Integer.parseInt(pageNow));
		map.put("page", page);

		map.addAttribute("teacher", teacher);
		List<Course> courses = courseServiceImpl.selectCourseByTeacher(teacher.getTeacherMobile());
		map.addAttribute("courses", courses);
		int messageCount = messageServiceImpl.selectMessageCount(teacherMobile);
		map.put("messageCount", messageCount);
		// List<Message> messages =
		// messageServiceImpl.selectUnreadMessage(teacherMobile,
		// page.getStartPos());
		// map.put("message", messages);
		return "teacherPage";
	}

	// ajax验证是否手机号已经注册过
	@SystemLog(module = "教师", methods = "日志管理-验证账号")
	@RequestMapping(value = "/confirmExitsTeacher.do")
	public @ResponseBody Map<String, Object> confirmExitsTeacher(HttpServletRequest request, String teacherMobile) {
		System.out.println(teacherMobile);
		request.getSession().setAttribute("UserId", null);
		request.getSession().setAttribute("UserId", teacherMobile);
		Map<String, Object> map = new HashMap<>();
		Teacher teacher = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
		if (teacher == null) {
			System.out.println("老师不存在");
			map.put("result", true);
		} else {
			map.put("teacher", teacher);
			map.put("result", false);
		}
		return map;
	}

	// ajax验证密码
	@SystemLog(module = "教师", methods = "日志管理-登录密码验证")
	@RequestMapping(value = "/confirmTeacherPassWord.do")
	@ResponseBody
	public Map<String, Object> confirmTeacherPassWord(String password, String teacherMobile) {
		Teacher teacher = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
		Map<String, Object> map = new HashMap<>();
		if (teacher != null && MD5Util.md5(password, "teacher").equals(teacher.getTeacherPassword())) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 教师注册
	@SystemLog(module = "教师", methods = "日志管理-注册")
	@RequestMapping(value = "/insertTeacher.do")
	public String insertTeacher(Teacher teacher, ModelMap map, HttpServletRequest request) {
		request.getSession().setAttribute("UserId", teacher.getTeacherMobile());
		String password = request.getParameter("teacherPassword");
		teacher.setTeacherPassword(MD5Util.md5(password, "teacher"));
		teacherServiceImpl.insertTeacher(teacher);
		String teacherMobile = request.getParameter("teacherMobile");
		Teacher teacher2 = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
		List<Course> courses = courseServiceImpl.selectCourseByTeacher(teacher.getTeacherMobile());
		map.addAttribute("courses", courses);
		map.addAttribute("teacher", teacher2);
		return "waitForTeacherRegister";
	}

	// 老师更改密码
	@SystemLog(module = "教师", methods = "日志管理-密码更改")
	@RequestMapping(value = "/updateTeacherPassWord.do")
	public String updateTeacherPassWord(String teacherMobile, String teacherPassword, ModelMap map) {
		teacherServiceImpl.updateTeacherPassWord(teacherMobile, MD5Util.md5(teacherPassword, "teacher"));
		List<Course> courses = courseServiceImpl.selectCourseByTeacher(teacherMobile);
		Teacher teacher = teacherServiceImpl.selectTeacherByEmail(teacherMobile);
		map.addAttribute("courses", courses);
		map.addAttribute("teacher", teacher);
		return "waitForTeacherRegister";
	}

	// 教师找回密码
	@SystemLog(module = "教师", methods = "日志管理-找回密码(跳转)")
	@RequestMapping(value = "/getTeacherPasswordBack.do")
	public String getTeacherPasswordBack() {
		return "teacherGetPasswordBack";
	}

	// 教师变更邮箱
	@SystemLog(module = "教师", methods = "日志管理-变更邮箱")
	@RequestMapping(value = "/changeTeaMail.do")
	@ResponseBody
	public Map<String, Object> changeTeaMail(String teacherEmail, String teacherMobile) {
		teacherServiceImpl.changeTeaMail(teacherMobile, teacherEmail);
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	// ajax试探教师信息
	@SystemLog(module = "教师", methods = "日志管理-找回密码(验证)")
	@RequestMapping(value = "/forTeacherReGetPass.do")
	@ResponseBody
	public Map<String, Object> forTeacherReGetPass(String mobile, String name, String mail) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Teacher teacher = teacherServiceImpl.selecctTeacherByThree(mobile, name, mail);
		if (teacher != null) {
			Random random = new Random();
			char a1 = (char) (random.nextInt(24) + 97);
			char a2 = (char) (random.nextInt(14) + 97);
			int x = random.nextInt(9000) + 1000;
			String cc = a1 + "" + x + a2;
			System.out.println(cc);
			teacherServiceImpl.updateTeacherPassWord(mobile, MD5Util.md5(cc, "teacher"));
			String EMAIL = mail;
			String TITLE = teacher.getTeacherName() + ",找回密码";// 标题
			String CONTENT = "临时密码为:" + "   " + "[" + cc + "]" + "\n" + "为了安全，请尽快使用此密码登录后修改密码"; // 内容
			JavaEmailSender.sendEmail(EMAIL, TITLE, CONTENT);
			map.put("result", true);
		} else {
			map.put("result", "查无此人");
		}
		return map;
	}

	// 查看具体消息
	@SystemLog(module = "教师", methods = "日志管理-查看消息")
	@RequestMapping(value = "/getMessageByAjax.do")
	@ResponseBody
	public Map<String, Object> getMessageByAjax(int messageId) {
		Map<String, Object> map = new HashMap<>();
		messageServiceImpl.uodateMesageHaveread(messageId);
		Message message = messageServiceImpl.selectMessageById(messageId);
		Student student = studentServiceImpl.selectStudentByNo(message.getMessageSender());
		if (student != null) {
			map.put("student", student);
		}
		map.put("mmm", message);
		return map;
	}

	// ajax获取消息数量
	@RequestMapping(value = "/gggetMessageCount.do")
	@ResponseBody
	public Map<String, Object> gggetMessageCount(String teacherMobile) {
		Map<String, Object> map = new HashMap<>();
		int messages = messageServiceImpl.selectMessageCount(teacherMobile);
		map.put("message", messages);
		return map;
	}

	// ajax更新寄教师信息
	@SystemLog(module = "教师", methods = "日志管理-完善信息")
	@RequestMapping(value = "/updateStudentInfoByAjax.do")
	@ResponseBody
	public Map<String, Object> updateStudentInfoByAjax(String teacherMobile, String college, String special,
			String schoolRecord, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDay, String freeStyle) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Teacher teacher = teacherServiceImpl.selectTeacherByMobile(teacherMobile);
		if (college != null) {
			teacher.setCollege(college);
		}
		if (special != null) {
			teacher.setSpecial(special);
		}
		if (birthDay != null) {
			teacher.setBirthDay(sdf2.format(birthDay));
		}
		if (freeStyle != null) {
			teacher.setFreeStyle(freeStyle);
		}
		if (schoolRecord != null) {
			teacher.setSchoolRecord(schoolRecord);
		}

		int tem = teacherServiceImpl.updateStudentextra(teacher);
		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 教师端多文件上传
	@SystemLog(module = "教师", methods = "日志管理-上传文件")
	@RequestMapping(value = "/teacherUpload.do")
	@ResponseBody
	public Map<String, Object> teacherUpload(@RequestParam("file") MultipartFile file, int courseId, String fileType,
			HttpServletRequest request) throws IOException, Throwable {
		Map<String, Object> map = new HashMap<>();
		System.out.println(courseId);
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (suffix.equals("zip") || suffix.equals("rar") || suffix.equals("7z")) {
			fileType = "压缩包";
		} else if (suffix.equals("pdf") || suffix.equals("xls") || suffix.equals("doc") || suffix.equals("ppt") || suffix.equals("docx")) {
			fileType = "文档";
		} else if (suffix.equals("gif") || suffix.equals("bmp") || suffix.equals("jpeg") || suffix.equals("png") || 
				suffix.equals("swf") || suffix.equals("svg") ||suffix.equals("jpg") || suffix.equals("tiff")) {
			fileType = "图片";
		} else if (suffix.equals("avi") || suffix.equals("mp4") || suffix.equals("rm") || suffix.equals("mov") || 
				suffix.equals("asf") || suffix.equals("wmv") ||suffix.equals("mkv") || suffix.equals("flv")) {
			fileType = "视频";
		} else if (suffix.equals("mp3") || suffix.equals("wma") || suffix.equals("wav") || suffix.equals("asf") || 
				suffix.equals("aac") || suffix.equals("mp3pro") ||suffix.equals("vqf") || suffix.equals("flac")) {
			fileType = "音频";
		} 
		
		// 定义上传路径
		String path = request.getSession().getServletContext().getRealPath("/") + "file";
		boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
		// 如果是文件上传类型
		if (isFileUpload) {
			// 得到文件上传工厂
			DiskFileItemFactory GongChang = new DiskFileItemFactory();
			// 处理文件上传核心类
			ServletFileUpload fileUpload = new ServletFileUpload(GongChang);
			// 设置文件上传类的编码格式
			fileUpload.setHeaderEncoding("UTF-8");
			// 得到文件名
			String time = new SimpleDateFormat("YYYY-MM-dd_HH_mm_ss").format(new Date());
			String time2 = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			String newfileName = fileName + "/" + time +"."+suffix;
			FilePackage filePackage = new FilePackage();
			filePackage.setCourseId(courseId);
			filePackage.setFileName(newfileName);
			filePackage.setFileType(fileType);
			filePackage.setCreateTime(time2);
			// 拼接一个新的文件名
			// 路径
			File targetFile = new File(path, newfileName);
			System.out.println(targetFile.getAbsolutePath());
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int tem = filePackageServiceImpl.insertFile(filePackage);
			if (tem > 0) {
				map.put("code", 0);
			}
		}
		return map;
	}

	// 获取文件列表
	@SystemLog(module = "教师", methods = "日志管理-获取文件列表")
	@RequestMapping(value = "/getPrivateData.do")
	@ResponseBody
	public Map<String, Object> getPrivateData(int courseId) {
		Map<String, Object> map = new HashMap<>();
		List<FilePackage> filePackages = filePackageServiceImpl.selectFileByCourseId(courseId);
		map.put("filePackages", filePackages);
		return map;
	}

	// 删除message
	@SystemLog(module = "教师", methods = "日志管理-删除消息")
	@RequestMapping(value = "/deleteMessage.do")
	@ResponseBody
	public Map<String, Object> deleteMessage(int messageId) {
		Map<String, Object> map = new HashMap<>();
		int tem = messageServiceImpl.deleteMessage(messageId);
		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}
	// 拒绝学生请假
	@SystemLog(module = "教师", methods = "日志管理-拒绝请假")
	@RequestMapping(value = "/cantLeave.do")
	@ResponseBody
	public Map<String, Object> cantLeave(int leaveRecordId,String teacherMobile,String teacherName,
			String studentRoNo,String content,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		LeaveRecord leaveRecord = leaveRecordServiceImpl.selectLeaveRecordByleaveRecordId(leaveRecordId);
		if(leaveRecord.getStatus().equals("待批")){
			Message message = new Message();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			message.setMessageSender(teacherMobile);
			message.setMessageAccepter(studentRoNo);
			message.setMessageTitle(teacherName + "老师拒绝了你的请假请求");
			message.setSendTime(sdf.format(new Date()));
			message.setHaveRead("未读");
			message.setMessageContent(content);
			message.setMessageType("leaveRecord");
			int tem = messageServiceImpl.insertMessage(message);
			String status = "未通过";
			int tem2 = leaveRecordServiceImpl.updateLeaveRecordByStudent(leaveRecordId,status);
			if(tem > 0 && tem2 >0){
				map.put("result", true);
				System.out.println("true");
			}
		}else{
			map.put("result", false);
		}		
		return map;
	}
	//同意学生请假
	@RequestMapping(value = "/agreeLeave.do")
	@ResponseBody
	public Map<String, Object> agreeLeave(int leaveRecordId,String teacherMobile,String studentRoNo,String content) {
		Map<String, Object> map = new HashMap<>();
		LeaveRecord leaveRecord = leaveRecordServiceImpl.selectLeaveRecordByleaveRecordId(leaveRecordId);
		if(leaveRecord.getStatus().equals("同意") || leaveRecord.getStatus().equals("未通过")){
			map.put("result", false);
		}else {
			String status = "同意";
			int tem = leaveRecordServiceImpl.updateLeaveRecordByStudent(leaveRecordId, status);
			Message message = new Message();
			Teacher teacher = teacherServiceImpl.selectTeacherByMobile(teacherMobile);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			message.setMessageSender(teacherMobile);
			message.setMessageAccepter(studentRoNo);
			message.setMessageTitle(teacher.getTeacherName() + "老师同意了你的请假请求");
			message.setSendTime(sdf.format(new Date()));
			message.setHaveRead("未读");
			message.setMessageContent(content);
			message.setMessageType("leaveRecord");
			int tem2 = messageServiceImpl.insertMessage(message);
			if(tem > 0 && tem2 > 0){
				map.put("result", true);
			}
		}
		
		return map;
	}
	
	//验证是否有必要弹出拒绝请假理由框
	@RequestMapping(value = "/confirmCantLeaveExist.do")
	@ResponseBody
	public Map<String, Object> confirmCantLeaveExist(int leaveRecordId,String teacherMobile,String studentRoNo) {
		Map<String, Object> map = new HashMap<>();
		Teacher teacher = teacherServiceImpl.selectTeacherByMobile(teacherMobile);
		System.out.println(leaveRecordId);
		System.out.println(teacher.getTeacherName());
		System.out.println(teacherMobile);
		System.out.println(studentRoNo);
		
		LeaveRecord leaveRecord = leaveRecordServiceImpl.selectLeaveRecordByleaveRecordId(leaveRecordId);
		if(leaveRecord.getStatus().equals("待批")){
			Message message = new Message();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			message.setMessageSender(teacherMobile);
			message.setMessageAccepter(studentRoNo);
			message.setMessageTitle(teacher.getTeacherName() + "老师拒绝了你的请假请求");
			message.setSendTime(sdf.format(new Date()));
			message.setHaveRead("未读");
			message.setMessageContent("系统消息：很遗憾，您的请假请求未被通过，若事情紧急请当面请假！");
			message.setMessageType("leaveRecord");
			int tem = messageServiceImpl.insertMessage(message);
			String status = "未通过";
			int tem2 = leaveRecordServiceImpl.updateLeaveRecordByStudent(leaveRecordId,status);
			if(tem > 0 && tem2 > 0){
				map.put("result", true);
			}
		}else {
			map.put("result", false);
		}
		return map;
	}
	//查看教师自己的博客
	@RequestMapping(value = "/toPersonBlog.do",method = RequestMethod.POST)
	public String toPersonBlog(String userRole,String userId,String userPassWord,ModelMap map,HttpServletRequest request,HttpServletResponse response){
		if(userRole.equals("teacher")){
			Teacher teacher = teacherServiceImpl.selectTeacherByEmail(userId);
			if(userPassWord != null && userPassWord.equals(teacher.getTeacherPassword())){
				map.put("teacher", teacher);
				List<MyBlog> myBlogs = myBlogServiceImpl.selectMyBlogByUserId(userId);
				map.put("myBlogs", myBlogs);
				return "personBlog";
			}else {
				return "failer";
			}	
		}else {
			return "failer";
		}	
			
	}
	
	// 个人博客图片上传
		@SystemLog(module = "教师", methods = "日志管理-上传文件")
		@RequestMapping(value = "/myBlogImg.do",method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> myBlogImg(@RequestParam("file") MultipartFile file,
				HttpServletRequest request) throws IOException, Throwable {
			JSONObject objData = new JSONObject();  
			if(file.getSize()/1024 <= 500){
			String fileName = file.getOriginalFilename();
			// 定义上传路径
			String path = request.getSession().getServletContext().getRealPath("/") + "blog";
			boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
			// 如果是文件上传类型
			if (isFileUpload) {
				// 得到文件上传工厂
				DiskFileItemFactory GongChang = new DiskFileItemFactory();
				// 处理文件上传核心类
				ServletFileUpload fileUpload = new ServletFileUpload(GongChang);
				// 设置文件上传类的编码格式
				fileUpload.setHeaderEncoding("UTF-8");
				// 得到文件名
				// 拼接一个新的文件名
				// 路径
				File targetFile = new File(path, fileName);
				System.out.println(targetFile.getAbsolutePath());
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					file.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(fileName != null && path != null){
					Map<String, Object> map2 = new HashMap<>();
					objData.put("code", 0);
					map2.put("src", "http://localhost:8080/ClassManageSys/blog/"+fileName);
					map2.put("title", fileName);
					objData.put("data", map2);
				}else {
					objData.put("msg", "插入图片失败");
				}
					
			}
		}else {
			objData.put("msg", "图片不得超过500kb");
		}
			return objData;
		}
		
		//教师端获取课件资料
		@RequestMapping(value = "/teacherGetFile.do")
		@ResponseBody
		public Map<String, Object> teacherGetFile(String fileName) {
			System.out.println(fileName);
			Map<String, Object> map = new HashMap<>();
			List<FilePackage> filePackages = filePackageServiceImpl.selectFile(fileName);
				map.put("result", true);
				map.put("filePackages", filePackages);
			return map;
		}
		//反馈
		@RequestMapping(value = "/insertFeedback.do")
		@ResponseBody
		public Map<String, Object> insertFeedback(String returnInfoToAdmin){
			System.out.println(returnInfoToAdmin);
			Map<String, Object> map = new HashMap<>();
			Feedback feedback = new Feedback();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm");
			feedback.setCurrentTime(sdf.format(new Date()));
			feedback.setFeedbackContent(returnInfoToAdmin);
			feedback.setResult("未解决");
			int tem = logEntityServiceImpl.insertFeedback(feedback);
			if(tem > 0){
				map.put("result", true);
			}
			else {
				map.put("result", false); 
			}
			return map;
		}
		
	//创建一个公告
		@RequestMapping(value = "/addProclamation.do")
		@ResponseBody
		public Map<String, Object> addProclamation(String messageContent,String teacherMobile,int courseId){
			Map<String, Object> map = new HashMap<>();
			List<StudentInfo> studentInfos = studentInfoServiceImpl.selectInfoFromInfoAndStudent(courseId);
			Message message = new Message();
			Teacher teacher = teacherServiceImpl.selectTeacherByMobile(teacherMobile);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			message.setMessageSender(teacherMobile);
			message.setMessageTitle(teacher.getTeacherName() + "发布了一个公告！");
			message.setSendTime(sdf.format(new Date()));
			message.setHaveRead("未读");
			message.setMessageContent(messageContent);
			message.setMessageType("publish");
			int tem = 0;
			for(StudentInfo studentInfo:studentInfos){
				message.setMessageAccepter(studentInfo.getStudent().getStudentRoNo());
				tem = messageServiceImpl.insertMessage(message);
			}
			if(tem > 0){
				map.put("result", true);
			}
			else {
				map.put("result", false); 
			}
			return map;
		}
		
	//从班级列表里删除学生
	@RequestMapping(value = "/deleteStudent.do")
	@ResponseBody
	public Map<String, Object> deleteStudent(String studentRoNo,int clazzId,int courseId){
		Map<String, Object> map = new HashMap<>();
		StudentInfo studentInfo = studentInfoServiceImpl.selectStudentInfoByMany(studentRoNo, courseId);
		ClazzStu clazzStu = clazzStuServiceImpl.selectClazzStuByCourse(studentRoNo, courseId);
		int tem = clazzStuServiceImpl.deleteClazzStuById(clazzStu.getClazzStuId());
		int tem2 = studentInfoServiceImpl.deleteStudentInfoById(studentInfo.getStudentInfoId());
		if((tem+tem2) > 1){
			map.put("result", true);
		}
		else {
			map.put("result", false); 
		}
		return map;
	}	

	//布置作业
	@RequestMapping(value = "/pushHomeWork.do")
	@ResponseBody
	public Map<String, Object> pushHomeWork(String homeWorkContent,@DateTimeFormat(pattern = "yyyy-MM-dd") Date homeWordDatetime,String teacherMobile,int courseId){
		Map<String, Object> map = new HashMap<>();
		List<StudentInfo> studentInfos = studentInfoServiceImpl.selectInfoFromInfoAndStudent(courseId);
		Message message = new Message();
		Teacher teacher = teacherServiceImpl.selectTeacherByMobile(teacherMobile);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		message.setMessageSender(teacherMobile);
		message.setMessageTitle(teacher.getTeacherName() + "老师布置了作业！");
		message.setSendTime(sdf.format(new Date()));
		message.setHaveRead("未读");
		message.setMessageContent("<span style='color:#FF5722'>完成时间："+ sdf.format(homeWordDatetime) +"！</span><br/>"+homeWorkContent);
		message.setMessageOther(sdf.format(homeWordDatetime));
		message.setMessageType("homeWork");
		int tem = 0;
		for(StudentInfo studentInfo:studentInfos){
			message.setMessageAccepter(studentInfo.getStudent().getStudentRoNo());
			tem = messageServiceImpl.insertMessage(message);
		}
		if(tem > 0){
			map.put("result", true);
		}
		else {
			map.put("result", false); 
		}
		return map;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
