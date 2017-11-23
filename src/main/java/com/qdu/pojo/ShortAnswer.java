package com.qdu.pojo;

import java.io.Serializable;

public class ShortAnswer implements Serializable{
	
	private int shortAnswerId;
	private int questionNumber; //题号
	private String shortAnswerContent; //问题
	private String questionsType; //问题类型
	private String studentAnswer; //学生答案
	private int value; //分值
	private String note; //备注
	private Examination examination;
	public int getShortAnswerId() {
		return shortAnswerId;
	}
	public void setShortAnswerId(int shortAnswerId) {
		this.shortAnswerId = shortAnswerId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getShortAnswerContent() {
		return shortAnswerContent;
	}
	public void setShortAnswerContent(String shortAnswerContent) {
		this.shortAnswerContent = shortAnswerContent;
	}
	public String getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(String questionsType) {
		this.questionsType = questionsType;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Examination getExamination() {
		return examination;
	}
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	
	
	

}
