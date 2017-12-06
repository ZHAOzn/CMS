package com.qdu.pojo;

import java.io.Serializable;

public class StudentAnswer implements Serializable{
	
	private int sAId;
	private String studentRoNo;
	private int examinationID;
	private int questionNumber;
	private String questionsType;
	private String stuAnswer;
	private int getValue;
	public int getsAId() {
		return sAId;
	}
	public void setsAId(int sAId) {
		this.sAId = sAId;
	}
	public String getStudentRoNo() {
		return studentRoNo;
	}
	public void setStudentRoNo(String studentRoNo) {
		this.studentRoNo = studentRoNo;
	}
	public int getExaminationID() {
		return examinationID;
	}
	public void setExaminationID(int examinationID) {
		this.examinationID = examinationID;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(String questionsType) {
		this.questionsType = questionsType;
	}
	public String getStuAnswer() {
		return stuAnswer;
	}
	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}
	public int getGetValue() {
		return getValue;
	}
	public void setGetValue(int getValue) {
		this.getValue = getValue;
	}
	
	
	

}
