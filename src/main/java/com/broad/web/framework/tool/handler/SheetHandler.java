package com.broad.web.framework.tool.handler;

import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

public class SheetHandler implements SheetWriteHandler{

	@Override
	public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
		
	}

	@Override
	public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
		SXSSFSheet sheet = (SXSSFSheet)writeSheetHolder.getSheet();
		sheet.setAutobreaks(true);
		sheet.trackAllColumnsForAutoSizing();//开启单元格自适应手动设置
	}

}
