package com.qdu.pojo;

import java.io.Serializable;

public class Judge implements Serializable{
	
	private int judgeId;
	private int questionNumber; //题号
	private String judgeContent; //问题
	private String questionsType; //问题类型
	private String studentAnswer; //学生答案
	private String answer;//正确答案
	private int value; //分值
	private String note; //备注
	private Examination examination;
	public int getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getJudgeContent() {
		return judgeContent;
	}
	public void setJudgeContent(String judgeContent) {
		this.judgeContent = judgeContent;
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
