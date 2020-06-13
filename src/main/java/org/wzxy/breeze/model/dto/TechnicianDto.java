package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Technician;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class TechnicianDto implements Serializable {
	  private int techId;
	  private String techName;
	  private String workSta;
	  private int depId;

	  private int nowPage;
	     private int pageSize;

	public TechnicianDto (Technician Tech) {
		 this.techId = Tech.getTechId();
		this.techName = Tech.getTechName();
		this.workSta = Tech.getWorkSta();
		this.depId = Tech.getDepId();
	}

	public TechnicianDto(int techId, String techName, String workSta, int depId, int nowPage, int pageSize) {
		this.techId = techId;
		this.techName = techName;
		this.workSta = workSta;
		this.depId = depId;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
	}

	public TechnicianDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getTechId() {
			return techId;
		}
		public void setTechId(int techId) {
			this.techId = techId;
		}
		public String getTechName() {
			return techName;
		}
		public void setTechName(String techName) {
			this.techName = techName;
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
		public String getWorkSta() {
			return workSta;
		}
		public void setWorkSta(String workSta) {
			this.workSta = workSta;
		}


}
