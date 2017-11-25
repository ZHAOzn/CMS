package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Examination;
import com.qdu.pojo.Judge;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.Pack;
import com.qdu.pojo.ShortAnswer;
import com.qdu.pojo.SingleSelection;

public interface ExaminationService {
	
	public List<Examination> selectExamination();
	
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
	
	public int selectJudgeCount(int examinationID);
	
	public int insertJudge(Judge judge);

	public int updateJudgeById(int judgeId, int questionNumber);
	
	public List<Judge> selectJudgeByExaminationID(int examinationID);
	
    public List<Pack> selectPackByExaminationIDX(int examinationID);
	
    public int insertPack(Pack pack);
	
	public int updatePackById(int packId,int questionNumber);
	
    public List<ShortAnswer> selectShortAnswerByExaminationIDX(int examinationID);
	
    public int insertShortAnswer(ShortAnswer shortAnswer);
	
	public int updateShortAnswerById(int shortAnswerId,int questionNumber);
	
    public int selectPackCount(int examinationID);
	
	public int selectShortAnswerCount(int examinationID);
	
	public int updateExaminationOfEdit(int examinationID,int canEdit);
	
	public int updateExaminationTemValue(int examinationID, int temValue);

}
