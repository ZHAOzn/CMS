package com.qdu.daoimpl;

import java.util.List;

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

}
