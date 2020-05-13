package org.wzxy.breeze.utils;

public  class DownloadUtils {


	/*@SuppressWarnings("resource")
	public static void  download(List<MaintenanceDto> list) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("实验室设备维修申请表");
		HSSFRow row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("申请编号");
		row1.createCell(1).setCellValue("实验室编号");
		row1.createCell(2).setCellValue("报修人编号");
		row1.createCell(3).setCellValue("报修人姓名");
		row1.createCell(4).setCellValue("设备编号");
		row1.createCell(5).setCellValue("设备名称");
		row1.createCell(6).setCellValue("设备型号");
		row1.createCell(7).setCellValue("故障现象描述");
		row1.createCell(8).setCellValue("报修时间");

		//遍历查询注入
		for(MaintenanceDto loopRecords : list) {

			HSSFRow row = sheet.createRow( sheet.getLastRowNum()+1 );
			row.createCell(0).setCellValue(loopRecords.getMainId() );
			row.createCell(1).setCellValue( loopRecords.getLabId() );
			row.createCell(2).setCellValue( loopRecords.getPersonId() );
			row.createCell(3).setCellValue( loopRecords.getPersonName() );
			row.createCell(4).setCellValue( loopRecords.getEquId());
			row.createCell(5).setCellValue( loopRecords.getEquName());
			row.createCell(6).setCellValue( loopRecords.getEqutype() );
			row.createCell(7).setCellValue( loopRecords.getDescription() );
			row.createCell(8).setCellValue( loopRecords.getReportDate());

		}
		//第三步 使用输出流进行文件下载
		String filename = "实验室设备维修申请表.xls";
		//获取文件类型
		String mimeType = ServletContext.getServletContext().getMimeType(filename);
		ServletOutputStream os = ServletContext.getResponse().getOutputStream();
		//设置文件类型
        ServletContext.getResponse().setContentType(mimeType);
		//设置客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		//使用工具类编码文件名（解决无法传递中文文件名的问题）
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		hssfWorkbook.write(os);
		}*/



}
