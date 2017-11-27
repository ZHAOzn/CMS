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
import com.qdu.pojo.Judge;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.Pack;
import com.qdu.pojo.ShortAnswer;
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
		if (examination.getCanEdit() == 0) {
			List<SingleSelection> singleSelections = examinationServiceImpl.selectSingleByExaminationID(examinationId);
			List<MoreSelection> moreSelections = examinationServiceImpl.selectMoreByExaminationID(examinationId);
			List<Judge> judges = examinationServiceImpl.selectJudgeByExaminationID(examinationId);
			List<Pack> packs = examinationServiceImpl.selectPackByExaminationIDX(examinationId);
			List<ShortAnswer> shortAnswers = examinationServiceImpl.selectShortAnswerByExaminationIDX(examinationId);
			map.put("result", true);
			map.put("examination", examination);
			map.put("singleSelections", singleSelections);
			map.put("moreSelections", moreSelections);
			map.put("judges", judges);
			map.put("packs", packs);
			map.put("shortAnswers", shortAnswers);
		} else {
			map.put("result", false);
		}
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
		List<Examination> examinations = examinationServiceImpl.selectExamination();
		int maxId = 0;
		if (examinations.size() > 0) {
			maxId = examinationServiceImpl.selectMaxExaminationIdByCourseId(courseId).getExaminationID();
		} else {
			maxId = 0;
		}
		String status = "C" + (maxId + 1) + "R" + (new Random().nextInt(90) + 10);
		System.out.println(status);
		Examination examination = new Examination();
		examination.setExaminationName(examinationName);
		examination.setTotalValue(totalValue);
		examination.setStartTime(sdf.format(startTime));
		examination.setDuration(duration);
		examination.setCourseID(courseId);
		examination.setOnlyCode(status);
		examination.setCanEdit(0);
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
		if ((examination.getTemValue() + Integer.parseInt(singleSelectionValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(Integer.parseInt(examinationId),
					examination.getTemValue() + Integer.parseInt(singleSelectionValue));
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

			// 多选题号
			int x = count + 1;
			List<MoreSelection> moreSelections = examinationServiceImpl
					.selectMoreByExaminationID(Integer.parseInt(examinationId));
			if (moreSelections.size() > 0) {
				for (int i = 0; i < moreSelections.size(); i++) {
					x++;
					examinationServiceImpl.updateMoreSelectionById(moreSelections.get(i).getMoreSelectionId(), x);
				}
			}

			// 判断题号
			int count2 = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
			int y = count + count2 + 1;
			List<Judge> judges = examinationServiceImpl.selectJudgeByExaminationID(Integer.parseInt(examinationId));
			if (judges.size() > 0) {
				for (int i = 0; i < judges.size(); i++) {
					y++;
					examinationServiceImpl.updateJudgeById(judges.get(i).getJudgeId(), y);
				}
			}

			// 填空题号
			int count3 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
			int z = count + count2 + count3 + 1;
			List<Pack> packs = examinationServiceImpl.selectPackByExaminationIDX(Integer.parseInt(examinationId));
			if (packs.size() > 0) {
				for (int i = 0; i < packs.size(); i++) {
					z++;
					examinationServiceImpl.updatePackById(packs.get(i).getPackId(), z);
				}
			}
			// 简答题号
			int count4 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
			int s = count + count2 + count3 + count4 + 1;
			List<ShortAnswer> shortAnswers = examinationServiceImpl
					.selectShortAnswerByExaminationIDX(Integer.parseInt(examinationId));
			if (shortAnswers.size() > 0) {
				for (int i = 0; i < shortAnswers.size(); i++) {
					s++;
					examinationServiceImpl.updateShortAnswerById(shortAnswers.get(i).getShortAnswerId(), s);
				}
			}

			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}

		return map;
	}

	// 添加多选题
	@RequestMapping(value = "/addMoreSelection.do")
	@ResponseBody
	public Map<String, Object> addMoreSelection(String examinationId, String questionContent, String MoreSelectionValue,
			String optionA, String optionB, String optionC, String optionD, String answer, String note) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("answer: " + answer);
		int count1 = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		int count = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		if ((examination.getTemValue() + Integer.parseInt(MoreSelectionValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(Integer.parseInt(examinationId),
					examination.getTemValue() + Integer.parseInt(MoreSelectionValue));
			MoreSelection moreSelection = new MoreSelection();
			moreSelection.setQuestionNumber(count1 + count + 1);
			moreSelection.setQuestionContent(questionContent);
			moreSelection.setOptionA(optionA);
			moreSelection.setOptionB(optionB);
			moreSelection.setOptionC(optionC);
			moreSelection.setOptionD(optionD);
			moreSelection.setAnswer(answer);
			moreSelection.setQuestionsType("多选");
			moreSelection.setValue(Integer.parseInt(MoreSelectionValue));
			moreSelection.setNote(note);
			moreSelection.setExamination(examination);
			int tem = examinationServiceImpl.insertMoreSelection(moreSelection);

			// 判断题题号
			int y = count + count1 + 1;
			List<Judge> judges = examinationServiceImpl.selectJudgeByExaminationID(Integer.parseInt(examinationId));
			if (judges.size() > 0) {
				for (int i = 0; i < judges.size(); i++) {
					y++;
					examinationServiceImpl.updateJudgeById(judges.get(i).getJudgeId(), y);
				}
			}
			// 填空题题号
			int count2 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
			int z = count + count1 + count2 + 1;
			List<Pack> packs = examinationServiceImpl.selectPackByExaminationIDX(Integer.parseInt(examinationId));
			if (packs.size() > 0) {
				for (int i = 0; i < packs.size(); i++) {
					z++;
					examinationServiceImpl.updatePackById(packs.get(i).getPackId(), z);
				}
			}
			// 简答题号
			int count3 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
			int s = count + count1 + count2 + count3 + 1;
			List<ShortAnswer> shortAnswers = examinationServiceImpl
					.selectShortAnswerByExaminationIDX(Integer.parseInt(examinationId));
			if (shortAnswers.size() > 0) {
				for (int i = 0; i < shortAnswers.size(); i++) {
					s++;
					examinationServiceImpl.updateShortAnswerById(shortAnswers.get(i).getShortAnswerId(), s);
				}
			}

			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}
		return map;
	}

	// 添加判断题
	@RequestMapping(value = "/addJudgeAnswer.do")
	@ResponseBody
	public Map<String, Object> addJudgeAnswer(String examinationId, String questionContent, String JudgeValue,
			String answer, String note) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("answer: " + answer);
		int count1 = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		int count2 = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		int count3 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
		int count4 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		if ((examination.getTemValue() + Integer.parseInt(JudgeValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(Integer.parseInt(examinationId),
					examination.getTemValue() + Integer.parseInt(JudgeValue));
			Judge judge = new Judge();
			judge.setJudgeContent(questionContent);
			judge.setQuestionNumber(count1 + count2 + count3 + 1);
			judge.setValue(Integer.parseInt(JudgeValue));
			judge.setQuestionsType("判断");
			judge.setAnswer(answer);
			judge.setNote(note);
			judge.setExamination(examination);
			int tem = examinationServiceImpl.insertJudge(judge);

			// 填空题题号
			int z = count1 + count2 + count3 + 1;
			List<Pack> packs = examinationServiceImpl.selectPackByExaminationIDX(Integer.parseInt(examinationId));
			if (packs.size() > 0) {
				for (int i = 0; i < packs.size(); i++) {
					z++;
					examinationServiceImpl.updatePackById(packs.get(i).getPackId(), z);
				}
			}

			// 简答题号
			int s = count1 + count2 + count3 + count4 + 1;
			List<ShortAnswer> shortAnswers = examinationServiceImpl
					.selectShortAnswerByExaminationIDX(Integer.parseInt(examinationId));
			if (shortAnswers.size() > 0) {
				for (int i = 0; i < shortAnswers.size(); i++) {
					s++;
					examinationServiceImpl.updateShortAnswerById(shortAnswers.get(i).getShortAnswerId(), s);
				}
			}

			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}
		return map;
	}

	// 添加填空题
	@RequestMapping(value = "/addPack.do")
	@ResponseBody
	public Map<String, Object> addPack(String examinationId, String questionContent, String PackValue, String answer,
			String note) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("answer: " + answer);
		int count1 = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		int count2 = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		int count3 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
		int count4 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		if ((examination.getTemValue() + Integer.parseInt(PackValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(Integer.parseInt(examinationId),
					examination.getTemValue() + Integer.parseInt(PackValue));
			Pack pack = new Pack();
			pack.setPackContent(questionContent);
			pack.setQuestionNumber(count1 + count2 + count3 + count4 + 1);
			pack.setValue(Integer.parseInt(PackValue));
			pack.setQuestionsType("填空");
			pack.setAnswer(answer);
			pack.setNote(note);
			pack.setExamination(examination);
			int tem = examinationServiceImpl.insertPack(pack);

			// 简答题号
			int s = count1 + count2 + count3 + count4 + 1;
			List<ShortAnswer> shortAnswers = examinationServiceImpl
					.selectShortAnswerByExaminationIDX(Integer.parseInt(examinationId));
			if (shortAnswers.size() > 0) {
				for (int i = 0; i < shortAnswers.size(); i++) {
					s++;
					examinationServiceImpl.updateShortAnswerById(shortAnswers.get(i).getShortAnswerId(), s);
				}
			}

			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}
		return map;
	}

	// 添加简答题
	@RequestMapping(value = "/addShortAnswer.do")
	@ResponseBody
	public Map<String, Object> addShortAnswer(String examinationId, String questionContent, String ShortAnswerValue,
			String note) {
		Map<String, Object> map = new HashMap<>();
		int count1 = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		int count2 = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		int count3 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
		int count4 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
		int count5 = examinationServiceImpl.selectShortAnswerCount(Integer.parseInt(examinationId));
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		if ((examination.getTemValue() + Integer.parseInt(ShortAnswerValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(Integer.parseInt(examinationId),
					examination.getTemValue() + Integer.parseInt(ShortAnswerValue));
			ShortAnswer shortAnswer = new ShortAnswer();
			shortAnswer.setShortAnswerContent(questionContent);
			shortAnswer.setQuestionNumber(count1 + count2 + count3 + count4 + +count5 + 1);
			shortAnswer.setValue(Integer.parseInt(ShortAnswerValue));
			shortAnswer.setQuestionsType("简答");
			shortAnswer.setNote(note);
			shortAnswer.setExamination(examination);
			int tem = examinationServiceImpl.insertShortAnswer(shortAnswer);
			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}
		return map;
	}

	// 更新试卷状态，改为不可修改
	@SystemLog(module = "教师", methods = "日志管理-提交试卷")
	@RequestMapping(value = "/updateEditStatus.do")
	@ResponseBody
	public Map<String, Object> updateEditStatus(String examinationId) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(examinationId);
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));
		int val = examination.getTemValue();
		int tem = -1;
		if (val == examination.getTotalValue()) {
			tem = examinationServiceImpl.updateExaminationOfEdit(Integer.parseInt(examinationId), 1);
		}
		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", examination.getTotalValue() - examination.getTemValue());
		}
		return map;
	}

	// 删除试卷
	@SystemLog(module = "教师", methods = "日志管理-删除试卷")
	@RequestMapping(value = "/deleteExamById.do")
	@ResponseBody
	public Map<String, Object> deleteExamById(String examinationId) {
		Map<String, Object> map = new HashMap<>();
		int tem1 = examinationServiceImpl.deleteSingleSelection(Integer.parseInt(examinationId));
		int tem2 = examinationServiceImpl.deleteMoreSelection(Integer.parseInt(examinationId));
		int tem3 = examinationServiceImpl.deleteJudge(Integer.parseInt(examinationId));
		int tem4 = examinationServiceImpl.deletePack(Integer.parseInt(examinationId));
		int tem5 = examinationServiceImpl.deleteShortAnswer(Integer.parseInt(examinationId));
		int tem6 = examinationServiceImpl.deleteExamination(Integer.parseInt(examinationId));
		if ((tem1 + tem2 + tem3 + tem4 + tem5 + tem6) > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 修改试卷
	// @SystemLog(module = "教师", methods = "日志管理-修改试卷")
	@RequestMapping(value = "/changeExamById.do")
	@ResponseBody
	public Map<String, Object> changeExamById(String examinationId, String examinationName,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS") Date startTime, int duration) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		int tem = examinationServiceImpl.updateExamination(examinationName, sdf.format(startTime), duration,
				Integer.parseInt(examinationId));
		if (tem > 0) {
			map.put("result", true);
			System.out.println("222");
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 查找单选题
	@RequestMapping(value = "/changeSingleSelection.do")
	@ResponseBody
	public Map<String, Object> changeSingleSelection(String singleSelectionId) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(singleSelectionId);
		SingleSelection singleSelection = examinationServiceImpl
				.selectSingleSelectionBysingleSelectionId(Integer.parseInt(singleSelectionId));
		if (singleSelection != null) {
			map.put("result", true);
			map.put("singleSelection", singleSelection);
		} else {
			map.put("result", false);
		}
		return map;
	}

	// 更新单选题
	@RequestMapping(value = "/saveSingleSelectionChange.do")
	@ResponseBody
	public Map<String, Object> saveSingleSelectionChange(String examinationId, int singleSelectionId,
			String questionContent, String singleSelectionValue, String optionA, String optionB, String optionC,
			String optionD, String answer, String note) {
		System.out.println(singleSelectionId);
		System.out.println(singleSelectionValue);
		Map<String, Object> map = new HashMap<>();
		SingleSelection singleSelection = examinationServiceImpl
				.selectSingleSelectionBysingleSelectionId(singleSelectionId);
		Examination examination = examinationServiceImpl
				.selectExaminationByExaminationId(Integer.parseInt(examinationId));

		if ((examination.getTemValue() - singleSelection.getValue()
				+ Integer.parseInt(singleSelectionValue)) <= examination.getTotalValue()) {
			examinationServiceImpl.updateExaminationTemValue(examination.getExaminationID(),
					(examination.getTemValue() - singleSelection.getValue() + Integer.parseInt(singleSelectionValue)));

			singleSelection.setQuestionContent(questionContent);
			singleSelection.setOptionA(optionA);
			singleSelection.setOptionB(optionB);
			singleSelection.setOptionC(optionC);
			singleSelection.setOptionD(optionD);
			singleSelection.setAnswer(answer);
			singleSelection.setQuestionsType("单选");
			singleSelection.setValue(Integer.parseInt(singleSelectionValue));
			singleSelection.setNote(note);
			int tem = examinationServiceImpl.updateSingleBysingleSelectionId(singleSelection);
			if (tem > 0) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", "no");
		}
		return map;
	}

	// 删除单选题
	@RequestMapping(value = "/deleteSingleSelection.do")
	@ResponseBody
	public Map<String, Object> deleteSingleSelection(String examinationId, int singleSelectionId) {
		Map<String, Object> map = new HashMap<>();
		SingleSelection singleSelection = examinationServiceImpl.selectSingleSelectionBysingleSelectionId(singleSelectionId);
		int a1 = singleSelection.getQuestionNumber();
		int tem = examinationServiceImpl.deleteSingleBySingleSelectionId(singleSelectionId);
        
		//删除单选以后，后面的题目题号向前缩进，通过update
		int count = examinationServiceImpl.selectSingleSelectionCount(Integer.parseInt(examinationId));
		List<SingleSelection> singleSelections = examinationServiceImpl.selectSingleByExaminationID(Integer.parseInt(examinationId));
		if(singleSelections.size() > 0){
			for(int i = 0; i < singleSelections.size(); i ++){
				if(singleSelections.get(i).getQuestionNumber() > a1){
					examinationServiceImpl.updateSingleSelectionById(singleSelectionId, singleSelections.get(i).getQuestionNumber()-1);
				}
			}
		}
		// 多选题号
		int x = count;
		List<MoreSelection> moreSelections = examinationServiceImpl
				.selectMoreByExaminationID(Integer.parseInt(examinationId));
		if (moreSelections.size() > 0) {
			for (int i = 0; i < moreSelections.size(); i++) {
				x++;
				examinationServiceImpl.updateMoreSelectionById(moreSelections.get(i).getMoreSelectionId(), x);
			}
		}

		// 判断题号
		int count2 = examinationServiceImpl.selectMoreSelectionCount(Integer.parseInt(examinationId));
		int y = count + count2;
		List<Judge> judges = examinationServiceImpl.selectJudgeByExaminationID(Integer.parseInt(examinationId));
		if (judges.size() > 0) {
			for (int i = 0; i < judges.size(); i++) {
				y++;
				examinationServiceImpl.updateJudgeById(judges.get(i).getJudgeId(), y);
			}
		}

		// 填空题号
		int count3 = examinationServiceImpl.selectJudgeCount(Integer.parseInt(examinationId));
		int z = count + count2 + count3;
		List<Pack> packs = examinationServiceImpl.selectPackByExaminationIDX(Integer.parseInt(examinationId));
		if (packs.size() > 0) {
			for (int i = 0; i < packs.size(); i++) {
				z++;
				examinationServiceImpl.updatePackById(packs.get(i).getPackId(), z);
			}
		}
		// 简答题号
		int count4 = examinationServiceImpl.selectPackCount(Integer.parseInt(examinationId));
		int s = count + count2 + count3 + count4;
		List<ShortAnswer> shortAnswers = examinationServiceImpl
				.selectShortAnswerByExaminationIDX(Integer.parseInt(examinationId));
		if (shortAnswers.size() > 0) {
			for (int i = 0; i < shortAnswers.size(); i++) {
				s++;
				examinationServiceImpl.updateShortAnswerById(shortAnswers.get(i).getShortAnswerId(), s);
			}
		}

		if (tem > 0) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}
}
