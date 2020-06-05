package com.broad.web.framework.tool.handler;

import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;

public class CellHandler implements CellWriteHandler {

	@Override
	public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
			Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
			Head head, Integer relativeRowIndex, Boolean isHead) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("rawtypes")
	public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
			List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
		SXSSFSheet sheet =  (SXSSFSheet) writeSheetHolder.getSheet();
		SXSSFWorkbook workbook = sheet.getWorkbook();
		XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
		Font cellFont = workbook.createFont();
		if (isHead) {
			cellFont.setBold(true);
			cellFont.setFontHeightInPoints((short)13);
		} else {
			cellFont.setFontHeightInPoints((short)12);
		}
//		cellStyle.setWrapText(Boolean.TRUE);
		cellStyle.setFont(cellFont);
		cellStyle.setBorderBottom(BorderStyle.NONE);
		cellStyle.setBorderLeft(BorderStyle.NONE);
		cellStyle.setBorderTop(BorderStyle.NONE);
		cellStyle.setBorderRight(BorderStyle.NONE);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cell.setCellStyle(cellStyle);
		cell.setAsActiveCell();
	}
}