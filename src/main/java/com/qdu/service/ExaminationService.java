package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Examination;

public interface ExaminationService {
	
	public int insertExamination(Examination examination);
	
	public int deleteExaminationById(int examinationID);
	
	public List<Examination> selectExaminationByCourseId(int courseId);
	
	public Examination selectMaxExaminationIdByCourseId(int courseId);
	
	public Examination selectExaminationByExaminationId(int examinationID);

}
