package com.broad.web.framework.pdf;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午4:49-2020/5/16
 * @Last modified by:
 */
@Data
public class PdfEntity {

    //文档标题
    private String title;
    //设置列数
    private int columns;
    //设置列宽比例
    private float[] columnWidths;
    //设置标题下内容名称
    private String[] littleTitleList;
    //设置标题下内容名称对应的参数名称
    private String[] littleTitleParam;
    //传入参数值，根据参数名称取参数值
    private Map<String,Object> littleTitleContent;
    //设置标题下内容的rowspan
    private int[] littleTitleRowSpan;
    //设置标题下内容的colspan
    private int[] littleTitleColSpan;
    //设置表格的表头
    private String[] tableTitle;
    //设置表头对应的字段名称
    private String[] tableParam;
    //设置表格内容的样式，“center”居中，“left”居左，“right”居右
    private String[] tableAlignStyle;
    //设置表头的rowspan
    private int[] tableTitleRowSpan;
    //设置表头的colspan
    private int[] tableTitleColSpan;
    //表格里的内容
    private List<Map<String,Object>> list;
    //设置表格下的内容名称
    private String[] bottomTitleList;
    //设置表格下的内容参数名称
    private String[] bottomParamList;
    //表格下的内容
    private Map<String,Object> bottomContent;
    //设置表格下内容的rowspan
    private int[] bottomRowSpan;
    //设置表格下内容的colspan
    private int[] bottomColSpan;
    //设置表格下的内容是否带边框
    private int[] bottomBorder;
    //文件所在的目录路径
    private String filePath;
    //文件名称
    private String fileName;
    //文件的全路径
    private String fullFilePath;
    //文档的方向，1 纸张横向 0 纸张纵向
    private int paperDirection;
    //内容到纸张的左边距
    private int marginLeft;
    //内容到纸张的右边距
    private int marginRight;
    //内容到纸张的上边距
    private int marginTop;
    //内容到纸张的下边距
    private int marginBottom;

}
