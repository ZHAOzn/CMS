package com.qdu.service;

import java.util.List;

import com.qdu.pojo.Message;

public interface MessageService { 

	public int insertMessage(Message message);
	
	public int selectMessageCount(String messageAccepter);
	
	public List<Message> selectUnreadMessage(String messageAccepter ,int startPos,int count);
	 
	public Message selectMessageById(int messageId);
	
	public void uodateMesageHaveread(int messageId);
	
	public int selectMessageTotalCount(String messageAccepter);
	
	public int deleteMessage(int messageId);
	
    public List<Message> selectSenderMessage(String messageSender,int startPos,int count);
	
	public int selectSendreMessageTotalCount(String messageSender);
}
