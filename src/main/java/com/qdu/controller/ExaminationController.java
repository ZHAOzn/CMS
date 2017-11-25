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
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.SingleSelection;
import com.qdu.service.ExaminationService;
import com.qdu.util.MD5Util;

@Controller
@RequestMapping(value = "exam")
public class ExaminationController {

	@Autowired
	ExaminationService examinationServiceImpl;

	// 查询试卷
	@RequestMapping("/selectExaminationByMyId.do")
	@ResponseBody
	public Map<String, Object> selectExaminationByMyId(int examinationId) {
		Map<String, Object> map = new HashMap<>();
		Examination examination = examinationServiceImpl.selectExaminationByExaminationId(examinationId);
		List<SingleSelection> singleSelections = examinationServiceImpl.selectSingleByExaminationID(examinationId);
		List<MoreSelection> moreSelections = examinationServiceImpl.selectMoreByExaminationID(examinationId);
		map.put("examination", examination);
		map.put("singleSelections", singleSelections);
		map.put("moreSelections", moreSelections);
		return map;
	}

	// 查询某课程下的试卷列表
	@RequestMapping("/selectExaminationByCourseId.do")
	@ResponseBody
	public Map<String, Object> selectExaminationByCourseId(int courseId) {
		Map<String, Object> map = new HashMap<>();
		List<Examination> examinations = examinationServiceImpl.selectExaminationByCourseId(courseId);
		if (examinations.size() == 0) {
			System.out.println("暂无试卷");
		}
		map.put("examinations", examinations);
		return map;
	}

	// 添加试卷
	@SystemLog(module = "教师", methods = "日志管理-添加试卷")
	@RequestMapping("/addExamination.do")
	@ResponseBody
	public Map<String, Object> addExamination(int courseId, String examinationName, int totalValue,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") Date startTime, int duration) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		System.out.println(courseId);
		System.out.println(examinationName);
		System.out.println(totalValue);
		int maxId = examinationServiceImpl.selectMaxExaminationIdByCourseId(courseId).getExaminationID();
		String status = "C" + (maxId + 1) + "R" + (new Random().nextInt(90) + 10);
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
		if (tem > 0) {
			map.put("result", true);
		}
		return map;
	}

	// 添加单选题
	@RequestMapping(value = "/addSingleSelection.do")
	@ResponseBody
	public Map<String, Object> addSingleSelection(String examinationId, String questionContent,
			String singleSelectionValue, String optionA, String optionB, String optionC, String optionD, String answer,
			String note) {
		Map<String, Object> map = new HashMap<>();
		int count = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		SingleSelection singleSelection = new SingleSelection();
		singleSelection.setQuestionNumber(count + 1);
		singleSelection.setQuestionContent(questionContent);
		singleSelection.setOptionA(optionA);
		singleSelection.setOptionB(optionB);
		singleSelection.setOptionC(optionC);
		singleSelection.setOptionD(optionD);
		singleSelection.setAnswer(answer);
		singleSelection.setQuestionsType("单选");
		singleSelection.setValue(Integer.parseInt(singleSelectionValue));
		singleSelection.setNote(note);
		singleSelection.setExamination(examination);
		int tem = examinationServiceImpl.insertSingleSelection(singleSelection);
		int x = count + 1;
		List<MoreSelection> moreSelections = examinationServiceImpl
				.selectMoreByExaminationID(Integer.parseInt(examinationId));
		if (moreSelections.size() > 0) {
			for (int i = 0; i < moreSelections.size(); i++) {
				x++;
				examinationServiceImpl.updateMoreSelectionById(moreSelections.get(i).getMoreSelectionId(), x);
			}
		}
		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 添加多选题
	@RequestMapping(value = "/addMoreSelection.do")
	@ResponseBody
	public Map<String, Object> addMoreSelection(String examinationId, String questionContent,
			String singleSelectionValue, String optionA, String optionB, String optionC, String optionD, String answer,
			String note) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("answer: " + answer);
		int count1 = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		int count = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		MoreSelection moreSelection = new MoreSelection();
		moreSelection.setQuestionNumber(count1 + count + 1);
		moreSelection.setQuestionContent(questionContent);
		moreSelection.setOptionA(optionA);
		moreSelection.setOptionB(optionB);
		moreSelection.setOptionC(optionC);
		moreSelection.setOptionD(optionD);
		moreSelection.setAnswer(answer);
		moreSelection.setQuestionsType("多选");
		moreSelection.setValue(Integer.parseInt(singleSelectionValue));
		moreSelection.setNote(note);
		moreSelection.setExamination(examination);
		int tem = examinationServiceImpl.insertMoreSelection(moreSelection);
		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}
}
