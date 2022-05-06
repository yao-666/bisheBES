package com.ruoyi.common.excel;

import com.alibaba.excel.read.listener.ReadListener;

import java.util.HashMap;

/**
 * Excel 导入监听
 *
 * @author Lion Li
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
