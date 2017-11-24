package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Examination;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.SingleSelection;

public interface ExaminationService {
	
	public int insertExamination(Examination examination);
	
	public int deleteExaminationById(int examinationID);
	
	public List<Examination> selectExaminationByCourseId(int courseId);
	
	public Examination selectMaxExaminationIdByCourseId(int courseId);
	
	public Examination selectExaminationByExaminationId(int examinationID);
	
	public int selectSingleSelectionCount(int examinationID);
	
	public int insertSingleSelection(SingleSelection selection); 
	
	public List<SingleSelection> selectSingleByExaminationID(int examinationID);
	
	public int selectMoreSelectionCount(int examinationID);

	public List<MoreSelection> selectMoreByExaminationID(int examinationID);

	public int insertMoreSelection(MoreSelection moreSelection);
	
	public int updateMoreSelectionById(int moreSelectionId,int questionNumber);

}
