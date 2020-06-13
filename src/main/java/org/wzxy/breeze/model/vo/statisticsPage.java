package org.wzxy.breeze.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class statisticsPage implements Serializable {
   int normalMoth;
   int lateMoth;
   int TruancyMoth;
   int normalDay;
   int lateDay;
   int TruancyDay;

	public statisticsPage() {
	}

	public int getNormalMoth() {
	return normalMoth;
}
public void setNormalMoth(int normalMoth) {
	this.normalMoth = normalMoth;
}
public int getLateMoth() {
	return lateMoth;
}
public void setLateMoth(int lateMoth) {
	this.lateMoth = lateMoth;
}
public int getTruancyMoth() {
	return TruancyMoth;
}
public void setTruancyMoth(int truancyMoth) {
	TruancyMoth = truancyMoth;
}
public int getNormalDay() {
	return normalDay;
}
public void setNormalDay(int normalDay) {
	this.normalDay = normalDay;
}
public int getLateDay() {
	return lateDay;
}
public void setLateDay(int lateDay) {
	this.lateDay = lateDay;
}
public int getTruancyDay() {
	return TruancyDay;
}
public void setTruancyDay(int truancyDay) {

	TruancyDay = truancyDay;
}

}
