package cn.tycoding.controller.pdf;

import cn.tycoding.controller.pdf.ItextExportPDF.FreemarkerUtils;
import cn.tycoding.controller.pdf.ItextExportPDF.Itext7Generator;
import cn.tycoding.pojo.User;
import cn.tycoding.service.IExcelExportService;
import cn.tycoding.service.UserService;
import cn.tycoding.util.CommonUtil;
import com.alibaba.fastjson.JSON;
import com.aspose.words.FontSettings;
import com.kmood.datahandle.DocumentProducer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

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
    @Resource
    private UserService userService;

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


    /**
     * 使用asposeword
     * 导出pdf
     */
    @RequestMapping(value = "/pdfExport")
    public void pdfExport(HttpServletRequest request, HttpServletResponse response)  {
        OutputStream out = null;
        try {
            //设置下载
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("测试导出", "UTF-8")+".pdf");
            out = response.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            this.testModel(bos);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            String classPath = this.getClass().getClassLoader().getResource("").toURI().getPath();
            //设置一个字体目录 TODO
//            FontSettings.setFontsFolder("/usr/local/software/windowsFont", false);
            FontSettings.setFontsFolder(classPath+"/exportTemplate/font", false);
            CommonUtil.doc2pdf(bis,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out !=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testModel(ByteArrayOutputStream bos) throws Exception {
        ClassLoader classLoader = ExcelExportController.class.getClassLoader();
        String ActualModelPath = classLoader.getResource("").toURI().getPath()+"template";
        String xmlPath = ActualModelPath;
        String ExportFilePath = classLoader.getResource("").toURI().getPath() + "test.docx";

        HashMap<String, Object> map = new HashMap<>();
        map.put("test1", "测试11111");
        map.put("test2", "测试22222");

        DocumentProducer dp = new DocumentProducer(ActualModelPath);
        String complie = dp.Complie(xmlPath, "test.xml", true);
//        dp.produce(map, ExportFilePath);
        dp.produce(map, bos);
    }


    /**
     * 使用itext
     * 导出pdf
     */
    @RequestMapping(value = "/pdfExportByItext7")
    public void pdfExportByItext7(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream is = null;
        OutputStream out = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("测试导出", "UTF-8")+".pdf");
            out = response.getOutputStream();

            String str = "{\"totalAverageScore\":79.83,\"villageName\":\"全部\",\"checkHousesAmount\":6,\"table\":[{\"safetyContent\":\"出租屋与生产、储存、经营场所\\r\\n是否设置在同一建筑物内\",\"yesAmount\":\"1\",\"noAmount\":\"5\",\"averageScore\":\"9.17分\"},{\"safetyContent\":\"出租屋是否有独立疏散楼梯\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"4.0分\"},{\"safetyContent\":\"直通楼顶天台安全出口是否上锁\",\"yesAmount\":\"2\",\"noAmount\":\"4\",\"averageScore\":\"4.33分\"},{\"safetyContent\":\"是否在疏散通道、走道、楼梯间处堆放杂物、停放摩托车、电动车等影响人疏散的物品\",\"yesAmount\":\"1\",\"noAmount\":\"5\",\"averageScore\":\"9.17分\"},{\"safetyContent\":\"出租屋人员居住部分存放液化石油气或居住部分与厨房等使用明火部分是否采用砖墙完全分隔\",\"yesAmount\":\"5\",\"noAmount\":\"1\",\"averageScore\":\"4.5分\"},{\"safetyContent\":\"出租屋内是否采用易燃、可燃材料进行分隔，设置夹层阁楼\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"5.33分\"},{\"safetyContent\":\"出租屋是否按要求配备灭火器材，应急照明，安全疏散标志\",\"yesAmount\":\"3\",\"noAmount\":\"3\",\"averageScore\":\"3.5分\"},{\"safetyContent\":\"出租屋是否按规定设置消防设施\",\"yesAmount\":\"5\",\"noAmount\":\"1\",\"averageScore\":\"4.17分\"},{\"safetyContent\":\"灭火器气压是否充足\",\"yesAmount\":\"5\",\"noAmount\":\"1\",\"averageScore\":\"4.17分\"},{\"safetyContent\":\"出租屋场所内电器线路的铺设是否暗线穿阻燃线管保护\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"4.0分\"},{\"safetyContent\":\"出租屋场所内电器线路使用年限是否为5年或以内\",\"yesAmount\":\"5\",\"noAmount\":\"1\",\"averageScore\":\"9.17分\"},{\"safetyContent\":\"出租屋房间是否设置阳台并安装防盗网\",\"yesAmount\":\"5\",\"noAmount\":\"1\",\"averageScore\":\"4.5分\"},{\"safetyContent\":\"出租屋楼层的窗户、阳台设置的防盗网，是否有应急逃生出口\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"4.0分\"},{\"safetyContent\":\"出租屋浴室是否有通风设备\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"3.33分\"},{\"safetyContent\":\"出租屋房内是否有电动车充电\",\"yesAmount\":\"3\",\"noAmount\":\"3\",\"averageScore\":\"2.5分\"},{\"safetyContent\":\"是否安装监控\",\"yesAmount\":\"4\",\"noAmount\":\"2\",\"averageScore\":\"4.0分\"},{\"safetyContent\":\"是否已购买出租屋保险\",\"yesAmount\":\"2\",\"noAmount\":\"4\",\"averageScore\":\"/\"}]}";
            HashMap map = JSON.parseObject(str,HashMap.class);
            String classPath = this.getClass().getClassLoader().getResource("").toURI().getPath();
            String templatePath = classPath+"/exportTemplate/";
            File file = new File(templatePath);
            String dataHtml = FreemarkerUtils.loadFtlHtml(file, "汇总模板.html", map);
//            File file_temp = new File(pdfExportPath+"summary"+(villageCode==null?"":("_"+villageCode) +".html"));
//            try (OutputStreamWriter ot = new OutputStreamWriter(new FileOutputStream(file_temp))) {
//                ot.write(dataHtml);//输出html
//            } catch (IOException e) {
//                throw new Exception("失败" + e);
//            }
            String fontPath = templatePath + "/font/Alibaba-PuHuiTi-Regular.otf";
            Itext7Generator itext7 = Itext7Generator.getInstanse(fontPath);
            //输出到服务器路径
            itext7.generatePdf(dataHtml, out);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }


    /**
     * 测试并发
     */
    @RequestMapping(value = "/concurrency")
    public void concurrency(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("查询余额:"+Thread.currentThread().getName());
        User user = userService.findAll().get(0);
        if(user.getBalance().compareTo(new BigDecimal("100")) == 0 ){
            System.out.println("开始充值");
            if(userService.updateForBalance(user) >0 ){
                System.out.println("充值成功");
            }else{
                System.out.println("已经充值，此次充值无效！");
            }
        }else{
            System.out.println("已经充值，请勿重复操作");
        }
    }

}
