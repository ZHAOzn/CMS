package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.SingleSelection;
import com.qdu.service.ExaminationService;

@Transactional
@Service
public class ExaminationServiceImpl implements ExaminationService{
	
	@Autowired private ExaminationDao examinationDaoImpl;

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
	
	

}
