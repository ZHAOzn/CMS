package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;
import com.qdu.pojo.Judge;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.Pack;
import com.qdu.pojo.ShortAnswer;
import com.qdu.pojo.SingleSelection;

@Repository
public class ExaminationDaoImpl implements ExaminationDao{
	
	@Autowired private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<Examination> selectExamination() {
		String statement = "com.qdu.mapping.ExaminationMapping.selectExamination";
		return sqlSessionFactory.openSession().selectList(statement);
	}

	@Override
	public int insertExamination(Examination examination) {
		String statement = "com.qdu.mapping.ExaminationMapping.insertExamination";
		return sqlSessionFactory.openSession().insert(statement, examination);
	}

	@Override
	public int deleteExaminationById(int examinationID) {
		String statement = "com.qdu.mapping.ExaminationMapping.deleteExaminationById";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public List<Examination> selectExaminationByCourseId(int courseId) {
		String statement = "com.qdu.mapping.ExaminationMapping.selectExaminationByCourseId";
		return sqlSessionFactory.openSession().selectList(statement, courseId);
	}

	@Override
	public Examination selectMaxExaminationIdByCourseId(int courseId) {
		String statement = "com.qdu.mapping.ExaminationMapping.selectMaxExaminationIdByCourseId";
		return sqlSessionFactory.openSession().selectOne(statement, courseId);
	}

	@Override
	public Examination selectExaminationByExaminationId(int examinationID) {
		String statement = "com.qdu.mapping.ExaminationMapping.selectExaminationByExaminationId";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public int selectSingleSelectionCount(int examinationID) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.selectSingleSelectionCount";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public int insertSingleSelection(SingleSelection selection) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.insertSingleSelection";
		return sqlSessionFactory.openSession().insert(statement, selection);
	}

	@Override
	public List<SingleSelection> selectSingleByExaminationID(int examinationID) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.selectSingleByExaminationID";
		return sqlSessionFactory.openSession().selectList(statement, examinationID);
	}

	@Override
	public int selectMoreSelectionCount(int examinationID) {
		String statement = "com.qdu.mapping.MoreSelectionMapping.selectMoreSelectionCount";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public List<MoreSelection> selectMoreByExaminationID(int examinationID) {
		String statement = "com.qdu.mapping.MoreSelectionMapping.selectMoreByExaminationID";
		return sqlSessionFactory.openSession().selectList(statement, examinationID);
	}

	@Override
	public int insertMoreSelection(MoreSelection moreSelection) {
		String statement = "com.qdu.mapping.MoreSelectionMapping.insertMoreSelection";
		return sqlSessionFactory.openSession().insert(statement, moreSelection);
	}

	@Override
	public int updateMoreSelectionById(int moreSelectionId,int questionNumber) {
		String statement = "com.qdu.mapping.MoreSelectionMapping.updateMoreSelectionById";
		Map<String, Object> map = new HashMap<>();
		map.put("moreSelectionId", moreSelectionId);
		map.put("questionNumber", questionNumber);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public int selectJudgeCount(int examinationID) {
		String statement = "com.qdu.mapping.JudgeMapping.selectJudgeCount";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public int insertJudge(Judge judge) {
		String statement = "com.qdu.mapping.JudgeMapping.insertJudge";
		return sqlSessionFactory.openSession().insert(statement, judge);
	}

	@Override
	public int updateJudgeById(int judgeId, int questionNumber) {
		String statement = "com.qdu.mapping.JudgeMapping.updateJudgeById";
		Map<String, Object> map = new HashMap<>();
		map.put("judgeId", judgeId);
		map.put("questionNumber", questionNumber);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public List<Judge> selectJudgeByExaminationID(int examinationID) {
		String statement = "com.qdu.mapping.JudgeMapping.selectJudgeByExaminationIDX";
		return sqlSessionFactory.openSession().selectList(statement, examinationID);
	}

	@Override
	public List<Pack> selectPackByExaminationIDX(int examinationID) {
		String statement = "com.qdu.mapping.PackMapping.selectPackByExaminationIDX";
		return sqlSessionFactory.openSession().selectList(statement, examinationID);
	}

	@Override
	public int insertPack(Pack pack) {
		String statement = "com.qdu.mapping.PackMapping.insertPack";
		return sqlSessionFactory.openSession().insert(statement, pack);
	}

	@Override
	public int updatePackById(int packId, int questionNumber) {
		String statement = "com.qdu.mapping.PackMapping.updatePackById";
		Map<String, Object> map = new HashMap<>();
		map.put("packId", packId);
		map.put("questionNumber", questionNumber);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public List<ShortAnswer> selectShortAnswerByExaminationIDX(int examinationID) {
		String statement = "com.qdu.mapping.ShortAnswerMapping.selectShortAnswerByExaminationIDX";
		return sqlSessionFactory.openSession().selectList(statement, examinationID);
	}

	@Override
	public int insertShortAnswer(ShortAnswer shortAnswer) {
		String statement = "com.qdu.mapping.ShortAnswerMapping.insertShortAnswer";
		return sqlSessionFactory.openSession().insert(statement, shortAnswer);
	}

	@Override
	public int updateShortAnswerById(int shortAnswerId, int questionNumber) {
		String statement = "com.qdu.mapping.ShortAnswerMapping.updateShortAnswerById";
		Map<String, Object> map = new HashMap<>();
		map.put("shortAnswerId", shortAnswerId);
		map.put("questionNumber", questionNumber);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public int selectPackCount(int examinationID) {
		String statement = "com.qdu.mapping.PackMapping.selectPackCount";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public int selectShortAnswerCount(int examinationID) {
		String statement = "com.qdu.mapping.ShortAnswerMapping.selectShortAnswerCount";
		return sqlSessionFactory.openSession().selectOne(statement, examinationID);
	}

	@Override
	public int updateExaminationOfEdit(int examinationID,int canEdit) {
		Map<String, Object> map = new HashMap<>();
		String statement = "com.qdu.mapping.ExaminationMapping.updateExaminationOfEdit";
		map.put("examinationID", examinationID);
		map.put("canEdit", canEdit);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public int updateExaminationTemValue(int examinationID, int temValue) {
		Map<String, Object> map = new HashMap<>();
		String statement = "com.qdu.mapping.ExaminationMapping.updateExaminationTemValue";
		map.put("examinationID", examinationID);
		map.put("temValue", temValue);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public int deleteJudge(int examinationID) {
		String statement = "com.qdu.mapping.JudgeMapping.deleteJudge";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int deletePack(int examinationID) {
		String statement = "com.qdu.mapping.PackMapping.deletePack";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int deleteShortAnswer(int examinationID) {
		String statement = "com.qdu.mapping.ShortAnswerMapping.deleteShortAnswer";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int deleteSingleSelection(int examinationID) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.deleteSingleSelection";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int deleteMoreSelection(int examinationID) {
		String statement = "com.qdu.mapping.MoreSelectionMapping.deleteMoreSelection";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int deleteExamination(int examinationID) {
		String statement = "com.qdu.mapping.ExaminationMapping.deleteExamination";
		return sqlSessionFactory.openSession().delete(statement, examinationID);
	}

	@Override
	public int updateExamination(String examinationName,String startTime, int duration,
			int examinationID) {
		String statement = "com.qdu.mapping.ExaminationMapping.updateExamination";
		Map<String, Object> map = new HashMap<>();
		map.put("examinationName", examinationName);
		map.put("startTime", startTime);
		map.put("duration", duration);
		map.put("examinationID", examinationID);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	@Override
	public SingleSelection selectSingleSelectionBysingleSelectionId(int singleSelectionId) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.selectSingleSelectionBysingleSelectionId";
		return sqlSessionFactory.openSession().selectOne(statement, singleSelectionId);
	}

	@Override
	public int updateSingleBysingleSelectionId(SingleSelection singleSelection) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.updateSingleBysingleSelectionId";
		return sqlSessionFactory.openSession().update(statement, singleSelection);
	}

	@Override
	public int deleteSingleBySingleSelectionId(int singleSelectionId) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.deleteSingleBySingleSelectionId";
		return sqlSessionFactory.openSession().delete(statement, singleSelectionId);
	}

	@Override
	public int updateSingleSelectionById(int singleSelectionId, int questionNumber) {
		String statement = "com.qdu.mapping.SingleSelectionMapping.updateSingleSelectionById";
		Map<String, Object> map = new HashMap<>();
		map.put("singleSelectionId", singleSelectionId);
		map.put("questionNumber", questionNumber);
		return sqlSessionFactory.openSession().update(statement, map);
	}

	
	
	

}
