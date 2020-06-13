package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.User;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserDto implements Serializable {

		 private int uid;
		 private int unum;
	     private String upwd;
	     private String definepwd;
	     private String utype;
	     private int nowPage;
	     private int pageSize;
	     private String userid;

	     public UserDto() {
	    	 super();
	     }

		public UserDto  (User userdto) {
			this.uid = userdto.getUid();
			this.unum = userdto.getUnum();
			this.upwd = userdto.getUpwd();
			this.utype = userdto.getUtype();
		}

		public UserDto(int uid, int unum, String upwd, String utype, int nowPage, int pageSize, String userid) {
			super();
			this.uid = uid;
			this.unum = unum;
			this.upwd = upwd;
			this.utype = utype;
			this.nowPage = nowPage;
			this.pageSize = pageSize;
			this.userid = userid;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}


		public int getUnum() {
			return unum;
		}

		public void setUnum(int unum) {
			this.unum = unum;
		}

		public String getUtype() {
			return utype;
		}

		public void setUtype(String utype) {
			this.utype = utype;
		}

		public String getUpwd() {
			return upwd;
		}

		public void setUpwd(String upwd) {
			this.upwd = upwd;
		}

		public int getNowPage() {
			return nowPage;
		}

		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getDefinepwd() {
			return definepwd;
		}

		public void setDefinepwd(String definepwd) {
			this.definepwd = definepwd;
		}

}
