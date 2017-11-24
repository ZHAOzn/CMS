package com.qdu.dao;

import java.util.List;

import com.qdu.pojo.Examination;

public interface ExaminationDao {
	
	public int insertExamination(Examination examination);
	
	public int deleteExaminationById(int examinationID);
	
	public List<Examination> selectExaminationByCourseId(int courseId);
	
	public Examination selectMaxExaminationIdByCourseId(int courseId);
	
	public Examination selectExaminationByExaminationId(int examinationID);

}
