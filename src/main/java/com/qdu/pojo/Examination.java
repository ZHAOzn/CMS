package com.qdu.pojo;

import java.io.Serializable;
import java.util.List;

//试卷
public class Examination  implements Serializable{
	private int examinationID;
	private String examinationName; //试卷名称
	private String onlyCode; //考试码
	private int totalValue; //总分值
	private int temValue;
	private String startTime; //考试开始时间
	private int duration;//考试时长
	private String examinationStatus;//试卷状态：待考/考试中/已考
	private int canEdit; //是否可以进行出题操作
	private int courseID; //所属的课程Id
	private List<SingleSelection> singleSelections;
	public List<MoreSelection> moreSelections;
	public List<Judge> judges;
	public List<Pack> packs;
	public List<ShortAnswer> shortAnswers;
	
	
	public int getExaminationID() {
		return examinationID;
	}
	public void setExaminationID(int examinationID) {
		this.examinationID = examinationID;
	}
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	public String getOnlyCode() {
		return onlyCode;
	}
	public void setOnlyCode(String onlyCode) {
		this.onlyCode = onlyCode;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
	public int getTemValue() {
		return temValue;
	}
	public void setTemValue(int temValue) {
		this.temValue = temValue;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getExaminationStatus() {
		return examinationStatus;
	}
	public void setExaminationStatus(String examinationStatus) {
		this.examinationStatus = examinationStatus;
	}
	public int getCanEdit() {
		return canEdit;
	}
	public void setCanEdit(int canEdit) {
		this.canEdit = canEdit;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public List<SingleSelection> getSingleSelections() {
		return singleSelections;
	}
	public void setSingleSelections(List<SingleSelection> singleSelections) {
		this.singleSelections = singleSelections;
	}
	public List<MoreSelection> getMoreSelections() {
		return moreSelections;
	}
	public void setMoreSelections(List<MoreSelection> moreSelections) {
		this.moreSelections = moreSelections;
	}
	public List<Judge> getJudges() {
		return judges;
	}
	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}
	public List<Pack> getPacks() {
		return packs;
	}
	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}
	public List<ShortAnswer> getShortAnswers() {
		return shortAnswers;
	}
	public void setShortAnswers(List<ShortAnswer> shortAnswers) {
		this.shortAnswers = shortAnswers;
	}
	
	

}
