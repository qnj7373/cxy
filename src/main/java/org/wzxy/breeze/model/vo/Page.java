package org.wzxy.breeze.model.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
	public class Page<T> implements Serializable {
		    private T commonObject;
		    private String commonFlag;
	        private int nowPage;
	        private int pageSize;
	        private int dataTotalCount;
	        private int pageTotalCount;
	        private List<T> datas;

	    	public Page() {

			}

			public Page(int nowPage, int pageSize, int dataTotalCount, int pageTotalCount, List<T> datas) {
				super();
				this.nowPage = nowPage;
				this.pageSize = pageSize;
				this.dataTotalCount = dataTotalCount;
				this.pageTotalCount = pageTotalCount;
				this.datas = datas;
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
				this.pageTotalCount=this.dataTotalCount%this.pageSize==0?this.dataTotalCount/this.pageSize:(this.dataTotalCount/this.pageSize)+1;
			}
			public int getDataTotalCount() {
				return dataTotalCount;
			}
			public void setDataTotalCount(int dataTotalCount) {
				this.dataTotalCount = dataTotalCount;
			}
			public int getPageTotalCount() {
				return pageTotalCount;
			}


			public void setPageTotalCount(int pageTotalCount) {
				this.pageTotalCount = pageTotalCount;
			}

			public List<T> getDatas() {
				return datas;
			}

			public void setDatas(List<T> datas) {
				this.datas = datas;
			}

			public T getCommonObject() {
				return commonObject;
			}

			public void setCommonObject(T commonObject) {
				this.commonObject = commonObject;
			}

			public String getCommonFlag() {
				return commonFlag;
			}

			public void setCommonFlag(String commonFlag) {
				this.commonFlag = commonFlag;
			}

}


