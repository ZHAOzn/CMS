package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;
import com.qdu.pojo.MoreSelection;
import com.qdu.pojo.SingleSelection;

@Repository
public class ExaminationDaoImpl implements ExaminationDao{
	
	@Autowired private SqlSessionFactory sqlSessionFactory;

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
	
	

}
