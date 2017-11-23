package com.qdu.pojo;

import java.io.Serializable;

public class MoreSelection implements Serializable{
	
	private int moreSelectionId;
	private int questionNumber; //题号
	private String questionContent; //问题
	private String questionsType; //问题类型
	private String optionA; //选项A
	private String optionB;
	private String optionC;
	private String optionD;
	private String studentAnswer; //学生答案
	private String answer;//正确答案
	private int value; //分值
	private String note; //备注
	private Examination examination;
	
	
	public int getMoreSelectionId() {
		return moreSelectionId;
	}
	public void setMoreSelectionId(int moreSelectionId) {
		this.moreSelectionId = moreSelectionId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(String questionsType) {
		this.questionsType = questionsType;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
