package com.qdu.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qdu.dao.MessageDao;
import com.qdu.pojo.Message;

@Repository 
public class MessageDaoImpl implements MessageDao{
	@Autowired 
	private SqlSessionFactory sessionFactory;

	@Override 
	public int insertMessage(Message message) {
		String statement = "com.qdu.mapping.MessageMapping.insertMessage";
		return sessionFactory.openSession().insert(statement, message);
	}

	@Override
	public int selectMessageCount(String messageAccepter) {
		String statement = "com.qdu.mapping.MessageMapping.selectMessageCount";
		return sessionFactory.openSession().selectOne(statement, messageAccepter);
	}

	@Override
	public List<Message> selectUnreadMessage(String messageAccepter ,int startPos,int count) {
		String statement = "com.qdu.mapping.MessageMapping.selectUnreadMessage";
		Map<String, Object> map = new HashMap<>();
		map.put("messageAccepter", messageAccepter);
		map.put("startPos", startPos);
		map.put("count", count);
		return sessionFactory.openSession().selectList(statement, map);
	}

	@Override
	public Message selectMessageById(int messageId) {
		String statement = "com.qdu.mapping.MessageMapping.selectMessageById";
		return sessionFactory.openSession().selectOne(statement, messageId);
	}

	@Override
	public void uodateMesageHaveread(int messageId) {
		String statement = "com.qdu.mapping.MessageMapping.uodateMesageHaveread";
		sessionFactory.openSession().update(statement, messageId);
	}

	@Override
	public int selectMessageTotalCount(String messageAccepter) {
		String statement = "com.qdu.mapping.MessageMapping.selectMessageTotalCount";
		return sessionFactory.openSession().selectOne(statement, messageAccepter);
	}

	@Override
	public int deleteMessage(int messageId) {
		String statement = "com.qdu.mapping.MessageMapping.deleteMessage";
		return sessionFactory.openSession().delete(statement, messageId);
	}

	@Override
	public List<Message> selectSenderMessage(String messageSender, int startPos, int count) {
		String statement = "com.qdu.mapping.MessageMapping.selectSenderMessage";
		Map<String, Object> map = new HashMap<>();
		map.put("messageSender", messageSender);
		map.put("startPos", startPos);
		map.put("count", count);
		return sessionFactory.openSession().selectList(statement, map);
	}

	@Override
	public int selectSendreMessageTotalCount(String messageSender) {
		String statement = "com.qdu.mapping.MessageMapping.selectSendreMessageTotalCount";
		return sessionFactory.openSession().selectOne(statement, messageSender);
	}

	

}
