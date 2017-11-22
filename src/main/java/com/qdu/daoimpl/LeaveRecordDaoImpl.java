package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.LeaveRecordDao;
import com.qdu.pojo.LeaveRecord;

@Repository
public class LeaveRecordDaoImpl implements LeaveRecordDao{
	
	@Autowired private SqlSessionFactory sqlsessionFactory;

	@Override
	public int insertleaveRecord(LeaveRecord leaveRecord) {
		String statement = "com.qdu.mapping.LeaveRecordMapping.insertleaveRecord";
		return sqlsessionFactory.openSession().insert(statement, leaveRecord);
	}

	@Override
	public List<LeaveRecord> selectLeaveRecordByStudent(String studentRoNo) {
		String statement = "com.qdu.mapping.LeaveRecordMapping.selectLeaveRecordByStudent";
		return sqlsessionFactory.openSession().selectList(statement, studentRoNo);
	}

	@Override
	public int updateLeaveRecordByStudent(int leaveRecordId,String status) {
		Map<String, Object> map = new HashMap<>();
		String statement = "com.qdu.mapping.LeaveRecordMapping.updateLeaveRecordById";
		map.put("leaveRecordId", leaveRecordId);
		map.put("status", status);
		return sqlsessionFactory.openSession().update(statement, map);
	}

	@Override
	public LeaveRecord selectLeaveRecordByleaveRecordId(int leaveRecordId) {
		String statement = "com.qdu.mapping.LeaveRecordMapping.selectLeaveRecordByleaveRecordId";
		return sqlsessionFactory.openSession().selectOne(statement, leaveRecordId);
	}

	@Override
	public LeaveRecord selectLeaveRecordByStudentLimit(String studentRoNo) {
		String statement = "com.qdu.mapping.LeaveRecordMapping.selectLeaveRecordByStudentLimit";
		return sqlsessionFactory.openSession().selectOne(statement, studentRoNo);
	}

	@Override
	public List<LeaveRecord> selectLeaveRecordByStudentAndCourse(String studentRoNo, int courseId, String status) {
		String statement = "com.qdu.mapping.LeaveRecordMapping.selectLeaveRecordByStudentAndCourse";
		Map<String, Object> map = new HashMap<>();
		map.put("studentRoNo", studentRoNo);
		map.put("courseId", courseId);
		map.put("status", status);
		return sqlsessionFactory.openSession().selectList(statement, map);
	}

}
