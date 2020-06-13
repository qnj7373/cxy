package org.wzxy.breeze.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class loginUser implements Serializable {

  private String uid;
  private int unum;
  private boolean powerSwitch ;
  private String upwd;
  private String definepwd;
  private String utype;
  private String loginResult;
  private String registerResult;

	public loginUser() {
	}

	public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public int getUnum() {
	return unum;
}
public void setUnum(int unum) {
	this.unum = unum;
}
public String getUpwd() {
	return upwd;
}
public void setUpwd(String upwd) {
	this.upwd = upwd;
}

public String getDefinepwd() {
	return definepwd;
}
public void setDefinepwd(String definepwd) {
	this.definepwd = definepwd;
}
public String getLoginResult() {
	return loginResult;
}
public void setLoginResult(String loginResult) {
	this.loginResult = loginResult;
}

public String getRegisterResult() {
	return registerResult;
}
public void setRegisterResult(String registerResult) {
	this.registerResult = registerResult;
}
public String getUtype() {
	return utype;
}
public void setUtype(String utype) {
	this.utype = utype;
}
public boolean isPowerSwitch() {
	return powerSwitch;
}
public void setPowerSwitch(boolean powerSwitch) {
	this.powerSwitch = powerSwitch;
}

}
