package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Class;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ClassDto implements Serializable {
	     private int classId;
	     private String className;
	     private int depId;

	     private int nowPage;
	     private int pageSize;


		public ClassDto() {
			super();
			// TODO Auto-generated constructor stub
		}

	public ClassDto (Class cla) {
		if(cla!=null) {
			this.classId=cla.getClassId();
			this.className=cla.getClassName();
			this.depId=cla.getDepId();
		}
	}

		public int getClassId() {
			return classId;
		}
		public void setClassId(int classId) {
			this.classId = classId;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public int getDepId() {
			return depId;
		}
		public void setDepId(int depId) {
			this.depId = depId;
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

}
