package com.sxt.baiwei.Utils;


import com.sxt.baiwei.bean.JObLevel;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PoiUtils {

    public static ResponseEntity<byte[]> exportJobLevel(List<JObLevel> allJoblevel) throws IOException {
//        创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();

//        创建文档的属性信息
        workbook.createInformationProperties();

//        获取文件创建信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();

        information.setCompany("sxt");
        information.setManager("javaboy");
        information.setCategory("职称表");
        HSSFSheet sheet1 = workbook.createSheet("职称表1");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
//      第一行创建表头
        HSSFRow row1 = sheet1.createRow(0);
        HSSFCell cell1 = row1.createCell(1);
        HSSFCell cell2 = row1.createCell(2);
        HSSFCell cell3 = row1.createCell(3);
        HSSFCell cell4 = row1.createCell(4);
        HSSFCell cell5 = row1.createCell(5);
        cell1.setCellValue("编号");
        cell2.setCellValue("职称名称");
        cell3.setCellValue("级别");

        cell4.setCellValue("创建时间");
        cell5.setCellValue("是否可用");

//        第二行添加数据
        for (int i = 0; i < allJoblevel.size(); i++) {
            JObLevel jOb = allJoblevel.get(i);
            HSSFRow row = sheet1.createRow(1 + i);
            HSSFCell cell11 = row.createCell(1);
                cell11.setCellValue(jOb.getId());
            HSSFCell cell21 = row.createCell(2);
                cell21.setCellValue(jOb.getName());
            HSSFCell cell31 = row.createCell(3);
                cell31.setCellValue(jOb.getTitlelevel());

            HSSFCell cell41 = row.createCell(4);
                cell41.setCellStyle(cellStyle);
                cell41.setCellValue(jOb.getCreatedate());
            HSSFCell cell51 = row.createCell(5);
                cell51.setCellValue(jOb.getEnabled()?"是":"否");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",new String("职称表.xls".getBytes("utf-8"),"iso-8859-1"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        ResponseEntity<byte[]> entity = new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.CREATED);

        return entity;
    }

    public static List<JObLevel> parse2List(MultipartFile file) throws IOException {
        ArrayList<JObLevel> list = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rows; i++) {
            HSSFRow row = sheet.getRow(i);
            // 获取id
            HSSFCell c1 = row.getCell(0);
//            获取名称
            HSSFCell c2 = row.getCell(1);
//            获取级别
            HSSFCell c3 = row.getCell(2);
//            获取创建时间
            HSSFCell c4 = row.getCell(3);
//            是否可用
            HSSFCell c5 = row.getCell(4);

            Integer cellValue1 =(int)c1.getNumericCellValue();
            String cellValue2 = c2.getStringCellValue();
            String cellValue3 = c3.getStringCellValue();
            Date cellValue4 = c4.getDateCellValue();
            String cellValue5 = c5.getStringCellValue();

            JObLevel jObLevel = new JObLevel();

            jObLevel.setId(cellValue1);
            jObLevel.setName(cellValue2);
            jObLevel.setTitlelevel(cellValue3);
            jObLevel.setCreatedate(cellValue4);
            jObLevel.setEnabled(cellValue5.equals("是"));

            list.add(jObLevel);
        }
        return list;
    }
}
