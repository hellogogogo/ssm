package Excel.ApachePOI;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class POI_03_Demo {

    String pathName = "C:/Users/Ly/Desktop/03.xls";


    /**
     * 03版测试
     * @throws Exception
     */
    @Test
    public void testWrite03() throws Exception {
        // 创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        Sheet sheet = workbook.createSheet("工作表1");
        // 创建一行
        Row row1 = sheet.createRow(0);
        // 创建一个单元格
        Cell cell11 = row1.createCell(0);
        // 设置单元格内容
        cell11.setCellValue("用户ID");
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("用户名");
        // 创建第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue(1);
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue("codewei");
        // 生成一张表（IO流）
        FileOutputStream fileOutputStream = new FileOutputStream(pathName);
        workbook.write(fileOutputStream);
        // 关闭流
        fileOutputStream.close();
        System.out.println("文件生成完毕,路径："+pathName);
    }

    /**
     *  03版大文件测试
     */
    @Test
    public void testWrite03BigData() throws Exception {
        long start = System.currentTimeMillis();
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for (int rowNum = 0;rowNum<65536;rowNum++){
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0;cellNum<10;cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(rowNum+"，"+cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream fileOutputStream = new FileOutputStream(pathName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("共花费了："+(end-start)/1000+"s");
    }

    /**
     * 读数据
     * @throws Exception
     */
    @Test
    public void testCellType() throws Exception{
        // 获取文件流
        FileInputStream in = new FileInputStream(pathName);
        // 读取到工作簿
        Workbook workbook = new HSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        // 获取标题内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle!=null){
            // 一行有多少列有数据
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0;cellNum<cellCount;cellNum++){
                Cell cell = rowTitle.getCell(cellNum);
                if (cell!=null){
                    CellType cellType = cell.getCellType();
                    String stringCellValue = cell.getStringCellValue();
                    System.out.print(stringCellValue + " | ");
                }
            }
            System.out.println();
        }
        // 获取表中的内容
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        for(int rowNum = 1;rowNum<physicalNumberOfRows;rowNum++){
            Row rowData = sheet.getRow(rowNum);
            if (rowData!=null){
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum=0;cellNum<cellCount;cellNum++){
                    System.out.print("[" +(rowNum+1) + "-"+(cellNum+1)+ "]");
                    Cell cell = rowData.getCell(cellNum);
                    if (cell!=null){
                        CellType cellType = cell.getCellType();
                        Object cellValue = "";
                        switch (cellType){
                            case STRING:
                                System.out.print("【String】");
                                cellValue = cell.getStringCellValue();
                                break;
                            case BOOLEAN:
                                System.out.print("【Boolean】");
                                cellValue = cell.getBooleanCellValue();
                                break;
                            case BLANK:
                                System.out.print("【Blank】");
                                break;
                            case NUMERIC:
                                System.out.print("【Numeric】");
                                if (HSSFDateUtil.isCellDateFormatted(cell)){
                                    // 日期
                                    System.out.print("【日期】");
                                    Date dateCellValue = cell.getDateCellValue();
                                    cellValue = new DateTime(dateCellValue).toString("yyyy-MM-dd");
                                }else {
                                    // 不是日期格式，防止数字过长
                                    System.out.print("【转换为字符串输出】");
                                    cell.setCellType(CellType.STRING);
                                    cellValue = cell.toString();
                                }
                                break;
                            case ERROR:
                                System.out.print("【Error】");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
                System.out.println();
            }
        }
        in.close();
    }
}
