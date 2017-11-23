package com.qdu.daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.ExaminationDao;
import com.qdu.pojo.Examination;

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
	
	

}
