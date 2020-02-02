package cn.tycoding.controller;

import cn.tycoding.service.IExcelExportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 用户的控制层
 *
 * @author tycoding
 * @date 18-4-7下午9:00
 */
@Controller
//@RequestMapping("/excel")
public class ExcelExportController {
    //注入service
    @Autowired
    private IExcelExportService excelExportService;

    /**
     * 导出excel
     */
    @RequestMapping(value = "/excelExport")
    public void excelExport(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long startTime = System.currentTimeMillis();
        List<TreeMap<String, Object>> baseList = excelExportService.export(2000000);
        //内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        wb.setCompressTempFiles(true); //压缩临时文件，很重要，否则磁盘很快就会被写满
        try {
//            Sheet sh = wb.createSheet();
//            for (int rownum = 0; rownum < baseList.size(); rownum++) {
//                Row row = sh.createRow(rownum);//新建一行
//                Map<String, Object> map = baseList.get(rownum);//取出每个map遍历
//                int cellnum = 0;//第几列
//                for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
//                    Cell cell = row.createCell(cellnum);
//                    cell.setCellValue(mapEntry.getValue().toString());
//                    cellnum++;
//                }
//            }

            Sheet sheet = null;		//工作表对象
            Row nRow = null;		//行对象
            Cell nCell = null;		//列对象
            int rowNo = 0;		//总行号
            int pageRowNo = 0;	//页行号
            int changePage = 1000000;	//多少页一个sheet

            for (int rownum = 0; rownum < baseList.size(); rownum++) {
                //打印300000条后切换到下个工作表，可根据需要自行拓展，2百万，3百万...数据一样操作，只要不超过1048576就可以
                if(rowNo%changePage==0){
                    System.out.println("Current Sheet:" + rowNo/changePage);
                    sheet = wb.createSheet("我的第"+((rowNo/changePage)+1)+"个工作簿");//建立新的sheet对象
                    sheet = wb.getSheetAt(rowNo/changePage);		//动态指定当前的工作表
                    pageRowNo = 0;		//每当新建了工作表就将当前工作表的行号重置为0
                }
                rowNo++;
                nRow = sheet.createRow(pageRowNo++);	//新建行对象
                // 打印每行，每行有6列数据   rsmd.getColumnCount()==6 --- 列属性的个数
                Map<String, Object> map = baseList.get(rownum);//取出每个map遍历
                int cellnum = 0;//第几列
                for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                    nCell = nRow.createCell(cellnum);
                    nCell.setCellValue(mapEntry.getValue().toString());
                    cellnum++;
                }
                if(rowNo%10000==0){
                    System.out.println("row no: " + rowNo);
                }
            }

//            方法1 生成临时文件在下载
//            FileOutputStream out = new FileOutputStream("D:/sxssf.xlsx");//生成文件
//            下载方法略..

            //方法2 用attachment方式下载
            OutputStream out = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("测试导出Excel", "UTF-8")+".xlsx");
            wb.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (wb != null) {
                wb.dispose();// 删除临时文件，很重要，否则磁盘可能会被写满
            }
            long endTime = System.currentTimeMillis();
            System.out.println("导出" + baseList.size() + "行，总时间:" + (endTime - startTime)/1000+"秒");
        }
    }
}
