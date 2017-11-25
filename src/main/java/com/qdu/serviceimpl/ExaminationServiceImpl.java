package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;
import com.qdu.pojo.Judge;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.Pack;
import com.qdu.pojo.ShortAnswer;
import com.qdu.pojo.SingleSelection;
import com.qdu.service.ExaminationService;

@Transactional
@Service
public class ExaminationServiceImpl implements ExaminationService{
	
	@Autowired private ExaminationDao examinationDaoImpl;
	
	@Override
	public List<Examination> selectExamination() {
		return examinationDaoImpl.selectExamination();
	}

	@Override
	public int insertExamination(Examination examination) {
		return examinationDaoImpl.insertExamination(examination);
	}

	@Override
	public int deleteExaminationById(int examinationID) {
		return examinationDaoImpl.deleteExaminationById(examinationID);
	}

	@Override
	public List<Examination> selectExaminationByCourseId(int courseId) {
		return examinationDaoImpl.selectExaminationByCourseId(courseId);
	}

	@Override
	public Examination selectMaxExaminationIdByCourseId(int courseId) {
		return examinationDaoImpl.selectMaxExaminationIdByCourseId(courseId);
	}

	@Override
	public Examination selectExaminationByExaminationId(int examinationID) {
		return examinationDaoImpl.selectExaminationByExaminationId(examinationID);
	}

	@Override
	public int selectSingleSelectionCount(int examinationID) {
		return examinationDaoImpl.selectSingleSelectionCount(examinationID);
	}

	@Override
	public int insertSingleSelection(SingleSelection selection) {
		return examinationDaoImpl.insertSingleSelection(selection);
	}

	@Override
	public List<SingleSelection> selectSingleByExaminationID(int examinationID) {
		return examinationDaoImpl.selectSingleByExaminationID(examinationID);
	}

	@Override
	public int selectMoreSelectionCount(int examinationID) {
		return examinationDaoImpl.selectMoreSelectionCount(examinationID);
	}

	@Override
	public List<MoreSelection> selectMoreByExaminationID(int examinationID) {
		return examinationDaoImpl.selectMoreByExaminationID(examinationID);
	}

	@Override
	public int insertMoreSelection(MoreSelection moreSelection) {
		return examinationDaoImpl.insertMoreSelection(moreSelection);
	}

	@Override
	public int updateMoreSelectionById(int moreSelectionId,int questionNumber) {
		return examinationDaoImpl.updateMoreSelectionById(moreSelectionId,questionNumber);
	}

	@Override
	public int selectJudgeCount(int examinationID) {
		return examinationDaoImpl.selectJudgeCount(examinationID);
	}

	@Override
	public int insertJudge(Judge judge) {
		return examinationDaoImpl.insertJudge(judge);
	}

	@Override
	public int updateJudgeById(int judgeId, int questionNumber) {
		return examinationDaoImpl.updateJudgeById(judgeId, questionNumber);
	}

	@Override
	public List<Judge> selectJudgeByExaminationID(int examinationID) {
		return examinationDaoImpl.selectJudgeByExaminationID(examinationID);
	}

	@Override
	public List<Pack> selectPackByExaminationIDX(int examinationID) {
		return examinationDaoImpl.selectPackByExaminationIDX(examinationID);
	}

	@Override
	public int insertPack(Pack pack) {
		return examinationDaoImpl.insertPack(pack);
	}

	@Override
	public int updatePackById(int packId, int questionNumber) {
		return examinationDaoImpl.updatePackById(packId, questionNumber);
	}

	@Override
	public List<ShortAnswer> selectShortAnswerByExaminationIDX(int examinationID) {
		return examinationDaoImpl.selectShortAnswerByExaminationIDX(examinationID);
	}

	@Override
	public int insertShortAnswer(ShortAnswer shortAnswer) {
		return examinationDaoImpl.insertShortAnswer(shortAnswer);
	}

	@Override
	public int updateShortAnswerById(int shortAnswerId, int questionNumber) {
		return examinationDaoImpl.updateShortAnswerById(shortAnswerId, questionNumber);
	}

	@Override
	public int selectPackCount(int examinationID) {
		return examinationDaoImpl.selectPackCount(examinationID);
	}

	@Override
	public int selectShortAnswerCount(int examinationID) {
		return examinationDaoImpl.selectShortAnswerCount(examinationID);
	}

	@Override
	public int updateExaminationOfEdit(int examinationID,int canEdit) {
		return examinationDaoImpl.updateExaminationOfEdit(examinationID,canEdit);
	}

	@Override
	public int updateExaminationTemValue(int examinationID, int temValue) {
		return examinationDaoImpl.updateExaminationTemValue(examinationID, temValue);
	}

	
	
	

}
