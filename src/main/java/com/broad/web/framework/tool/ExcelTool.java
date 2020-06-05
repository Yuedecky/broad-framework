package com.broad.web.framework.tool;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.broad.web.framework.constant.EasyExcelHandle;
import com.broad.web.framework.tool.handler.CellHandler;
import com.broad.web.framework.tool.handler.SheetHandler;
import com.broad.web.framework.tool.handler.WorkbookHandler;
import com.broad.web.framework.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class ExcelTool {

    public static <T> void export(HttpServletResponse response, String excelName,
                                  ExcelTypeEnum excelType, EasyExcelHandle<T> handle, Class<T> cls) throws IOException {
        if (null == excelType) {
            excelType = ExcelTypeEnum.XLSX;
        }
        if (StringUtils.isBlank(excelName)) {
            excelName = System.currentTimeMillis() + "";
        }
        try {
            excelName += DateTool.convert2String(new Date(), "yyyy-MM-dd-HHmmss") + excelType.getValue();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8"));
            EasyExcel.write(response.getOutputStream(), cls)
                    .registerWriteHandler(new WorkbookHandler())
                    .registerWriteHandler(new SheetHandler())
                    .registerWriteHandler(new CellHandler())
                    .useDefaultStyle(Boolean.FALSE)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("one")
                    .doWrite(handle.getData());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.reset();
        }
    }

}
