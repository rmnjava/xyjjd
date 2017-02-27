package com.redstar.jjd.utils;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

public class ExportUtil {
    private static final Logger LOGGER = Logger.getLogger(ExportUtil.class);

    public static void exportbyurl(String fileName, WritableWorkbook workbook,
            HttpServletResponse response) {
        try {
            response.setContentType("applicationnd.ms-excel");
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=\""
                    + new String(fileName.getBytes("GBK"), "ISO8859_1") + "\";");
            workbook.write();
            workbook.close();
            response.getOutputStream().close();
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }
}
