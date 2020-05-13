package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {

	    private int uid;
	    private int unum;
        private String upwd;
        private String utype;
	    private List<role> roles;

        public User() {
        	super();
        }




		public User(int uid, int unum, String upwd, String utype) {
			super();
			this.uid = uid;
			this.unum = unum;
			this.upwd = upwd;
			this.utype = utype;
		}


	public List<role> getRoles() {
		return roles;
	}

	public void setRoles(List<role> roles) {
		this.roles = roles;
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



		public String getUpwd() {
			return upwd;
		}

		public void setUpwd(String upwd) {
			this.upwd = upwd;
		}

		public String getUtype() {
			return utype;
		}

		public void setUtype(String utype) {
			this.utype = utype;
		}


}
