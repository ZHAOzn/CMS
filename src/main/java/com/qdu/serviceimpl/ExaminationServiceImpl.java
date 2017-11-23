package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;
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
	
	

}
