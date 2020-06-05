package com.broad.web.framework.tool.handler;

import com.broad.web.framework.utils.ThreadPoolUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;

public class RowHandler implements RowWriteHandler {

	@Override
	public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Integer rowIndex,
			Integer relativeRowIndex, Boolean isHead) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
			Integer relativeRowIndex, Boolean isHead) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
			Integer relativeRowIndex, Boolean isHead) {
		SXSSFSheet sheet = (SXSSFSheet) row.getSheet();
		if((relativeRowIndex+1)%SXSSFWorkbook.DEFAULT_WINDOW_SIZE == 0) {
			ThreadPoolUtils.getThreadPoolTaskExecutor().submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						sheet.autoSizeColumn(j);
					}
				}
			});
		}
	}

}
