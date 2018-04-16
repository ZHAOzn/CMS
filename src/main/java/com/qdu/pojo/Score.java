package com.qdu.pojo;

import java.io.Serializable;

public class Score implements Serializable{

	 private int scoreId;
	 private String studentRoNo;
	 private String studentName;
	 private String studentClass; 
	 private int examinationID;
	 private int singleSelectionValue;
	 private int moreSelectionValue;
	 private int judgeValue;
	 private int packValue;
	 private int shortAnswerValue;
	 private int totalValue;
	 private int examEnd;
	 private String scoreStatus;
	 private String BeforeExamPhoto;
	 private String ExamingPhotoOne;
	 private String ExamingPhotoTwo;
	 private String ExamingPhotoThree;
	 
	 
	 
	public String getBeforeExamPhoto() {
		return BeforeExamPhoto;
	}
	public void setBeforeExamPhoto(String beforeExamPhoto) {
		BeforeExamPhoto = beforeExamPhoto;
	}
	public String getExamingPhotoOne() {
		return ExamingPhotoOne;
	}
	public void setExamingPhotoOne(String examingPhotoOne) {
		ExamingPhotoOne = examingPhotoOne;
	}
	public String getExamingPhotoTwo() {
		return ExamingPhotoTwo;
	}
	public void setExamingPhotoTwo(String examingPhotoTwo) {
		ExamingPhotoTwo = examingPhotoTwo;
	}
	public String getExamingPhotoThree() {
		return ExamingPhotoThree;
	}
	public void setExamingPhotoThree(String examingPhotoThree) {
		ExamingPhotoThree = examingPhotoThree;
	}
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public String getStudentRoNo() {
		return studentRoNo;
	}
	public void setStudentRoNo(String studentRoNo) {
		this.studentRoNo = studentRoNo;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public int getExaminationID() {
		return examinationID;
	}
	public void setExaminationID(int examinationID) {
		this.examinationID = examinationID;
	}
	public int getSingleSelectionValue() {
		return singleSelectionValue;
	}
	public void setSingleSelectionValue(int singleSelectionValue) {
		this.singleSelectionValue = singleSelectionValue;
	}
	public int getMoreSelectionValue() {
		return moreSelectionValue;
	}
	public void setMoreSelectionValue(int moreSelectionValue) {
		this.moreSelectionValue = moreSelectionValue;
	}
	public int getJudgeValue() {
		return judgeValue;
	}
	public void setJudgeValue(int judgeValue) {
		this.judgeValue = judgeValue;
	}
	public int getPackValue() {
		return packValue;
	}
	public void setPackValue(int packValue) {
		this.packValue = packValue;
	}

	public int getShortAnswerValue() {
		return shortAnswerValue;
	}
	public void setShortAnswerValue(int shortAnswerValue) {
		this.shortAnswerValue = shortAnswerValue;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	public int getExamEnd() {
		return examEnd;
	}
	public void setExamEnd(int examEnd) {
		this.examEnd = examEnd;
	}
	public String getScoreStatus() {
		return scoreStatus;
	}
	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	 
	 
}
