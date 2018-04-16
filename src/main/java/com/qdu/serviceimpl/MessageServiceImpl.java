package com.qdu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qdu.dao.MessageDao;
import com.qdu.pojo.Message;
import com.qdu.service.MessageService;

@Service
@Transactional 
public class MessageServiceImpl implements MessageService{
     
	@Autowired private MessageDao messageDaoImpl;
	 
	@Override
	public int insertMessage(Message message) {
		return messageDaoImpl.insertMessage(message);
	}

	@Override
	public int selectMessageCount(String messageAccepter) {
		return messageDaoImpl.selectMessageCount(messageAccepter);
	}

	

	@Override
	public Message selectMessageById(int messageId) {
		return messageDaoImpl.selectMessageById(messageId);
	}

	@Override
	public void uodateMesageHaveread(int messageId) {
		messageDaoImpl.uodateMesageHaveread(messageId);
	}

	@Override
	public int selectMessageTotalCount(String messageAccepter) {
		return messageDaoImpl.selectMessageTotalCount(messageAccepter);
	}

	@Override
	public List<Message> selectUnreadMessage(String messageAccepter, int startPos, int count) {
		return messageDaoImpl.selectUnreadMessage(messageAccepter, startPos, count);
	}

	@Override
	public int deleteMessage(int messageId) {
		return messageDaoImpl.deleteMessage(messageId);
	}

	@Override
	public List<Message> selectSenderMessage(String messageSender, int startPos, int count) {
		return messageDaoImpl.selectSenderMessage(messageSender, startPos, count);
	}

	@Override
	public int selectSendreMessageTotalCount(String messageSender) {
		return messageDaoImpl.selectSendreMessageTotalCount(messageSender);
	}

}
