package com.broad.web.framework.tool.handler;

import java.util.Date;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.alibaba.excel.write.handler.WorkbookWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.tool.DateTool;

@Slf4j
public class WorkbookHandler implements WorkbookWriteHandler {

	private static final ThreadLocal<Long> timeThreadLocal = new ThreadLocal<Long>();

	@Override
	public void beforeWorkbookCreate() {
		log.info("开始导出excel，{}", DateTool.convert2String(new Date()));
		timeThreadLocal.set(System.currentTimeMillis());
	}

	@Override
	public void afterWorkbookCreate(WriteWorkbookHolder writeWorkbookHolder) {
	}

	@Override
	public void afterWorkbookDispose(WriteWorkbookHolder writeWorkbookHolder) {
		try {
			SXSSFWorkbook workbook = (SXSSFWorkbook) writeWorkbookHolder.getWorkbook();
			if(null != workbook && workbook.getNumberOfSheets() > 0) {
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					SXSSFSheet sheet = workbook.getSheetAt(i);
					if (null != sheet) {
						SXSSFRow row = sheet.getRow(0);
						if (null != row && row.getPhysicalNumberOfCells() > 0) {
							for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
								sheet.autoSizeColumn(j);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		log.info("导出excel结束，{}，耗时：{}", DateTool.convert2String(new Date()),
				DateTool.convert2times(System.currentTimeMillis() - timeThreadLocal.get()));
		timeThreadLocal.remove();
	}

}
