package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdu.aop.SystemLog;
import com.qdu.pojo.Examination;
import com.qdu.service.ExaminationService;
import com.qdu.util.MD5Util;

@Controller
@RequestMapping(value = "exam")
public class ExaminationController {
	
	@Autowired ExaminationService examinationServiceImpl;
	
	//查询试卷
		@RequestMapping("/selectExaminationByMyId.do")
		@ResponseBody
		public Map<String, Object> selectExaminationByMyId(int examinationId){
			Map<String, Object> map = new HashMap<>();
			Examination examination = examinationServiceImpl.selectExaminationByExaminationId(examinationId);
			map.put("examination", examination);
			return map;
		}
	
	//查询某课程下的试卷列表
	@RequestMapping("/selectExaminationByCourseId.do")
	@ResponseBody
	public Map<String, Object> selectExaminationByCourseId(int courseId){
		Map<String, Object> map = new HashMap<>();
		List<Examination> examinations = examinationServiceImpl.selectExaminationByCourseId(courseId);
		if(examinations.size() == 0){
			System.out.println("暂无试卷");
		}
		map.put("examinations", examinations);
		return map;
	}
	//添加试卷
	@SystemLog(module = "教师", methods = "日志管理-添加试卷")	
	@RequestMapping("/addExamination.do")
	@ResponseBody
	public Map<String, Object> addExamination(int courseId,String examinationName,int totalValue,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") Date startTime,
			int duration){
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		System.out.println(courseId);
		System.out.println(examinationName);
		System.out.println(totalValue);
		int maxId = examinationServiceImpl.selectMaxExaminationIdByCourseId(courseId).getExaminationID();
		String status = "C" + (maxId+1) + "R" + (new Random().nextInt(90) + 10);
		System.out.println(status);
		Examination examination = new Examination();
		examination.setExaminationName(examinationName);
		examination.setTotalValue(totalValue);
		examination.setStartTime(sdf.format(startTime));
		examination.setDuration(duration);
		examination.setCourseID(courseId);
		examination.setOnlyCode(status);
		System.out.println("11111");
		int tem = examinationServiceImpl.insertExamination(examination);
		System.out.println(tem);
		if(tem > 0){
		map.put("result", true);
		}
		return map;
	}
	

}
