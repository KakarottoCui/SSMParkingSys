package com.depot.ex.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;


public class Export {

	public String createExcel(HttpServletResponse response) throws IOException {

		// ����HSSFWorkbook����(excel���ĵ�����)
		HSSFWorkbook wb = new HSSFWorkbook();
		// �����µ�sheet����excel�ı���
		HSSFSheet sheet = wb.createSheet("�ɼ���");
		// ��sheet�ﴴ����һ�У�����Ϊ������(excel����)��������0��65535֮����κ�һ��
		HSSFRow row1 = sheet.createRow(0);
		// ������Ԫ��excel�ĵ�Ԫ�񣬲���Ϊ��������������0��255֮����κ�һ��
		HSSFCell cell = row1.createCell(0);
		// ���õ�Ԫ������
		cell.setCellValue("ѧԱ���Գɼ�һ����");
		// �ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// ��sheet�ﴴ���ڶ���
		HSSFRow row2 = sheet.createRow(1);
		// ������Ԫ�����õ�Ԫ������
		row2.createCell(0).setCellValue("����");
		row2.createCell(1).setCellValue("�༶");
		row2.createCell(2).setCellValue("���Գɼ�");
		row2.createCell(3).setCellValue("���Գɼ�");
		// ��sheet�ﴴ��������
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("����");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		// .....ʡ�Բ��ִ���

		// ���Excel�ļ�
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=details.xls");
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
		return null;
	}
}
