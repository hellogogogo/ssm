package cn.tycoding.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: liuyu
 * @Date: 2021/4/2 10:46
 */
public class CommonUtil {

    /**
     * localDateTime转string
     * @param date
     * @return
     */
    public static String ConvertDate(LocalDateTime date){
        DateTimeFormatter dfm = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss");
        return dfm.format(date);
    }

    /**
     * 出租屋办证情况
     * @param getCertificateFlag  出租屋办证情况代码
     * @return
     */
    public static String returnCertificateFlag(String getCertificateFlag){
        //出租屋办证情况：1--登记证,2--备案证,3--无证,12-登记证、备案证均有
        String certificateName = null;
        if(getCertificateFlag !=null ){
            switch (getCertificateFlag){
                case "1":
                    certificateName = "登记证";
                    break;
                case "2":
                    certificateName = "备案证";
                    break;
                case "3":
                    certificateName = "无证";
                    break;
                case "12":
                    certificateName = "登记证、备案证均有";
                    break;
                default:
                    certificateName = "";
            }
        }else{
            certificateName = "";
        }
        return certificateName;
    }

    /**
     * 返回证件类型
     * @param idType 证件类型
     */
    public static String returnIdTypeString(String idType){
        String idTypeName = "";
        if(idType == null){
            return idTypeName;
        }
        switch (idType){
            case "01":
                idTypeName = "身份证";
                break;
            case "02":
                idTypeName = "户口薄";
                break;
            case "03":
                idTypeName = "护照";
                break;
            case "04":
                idTypeName = "军人证件";
                break;
            case "05":
                idTypeName = "驾驶执照";
                break;
            case "06":
                idTypeName = "返乡证";
                break;
            case "07":
                idTypeName = "港澳身份证";
                break;
            case "08":
                idTypeName = "工号";
                break;
            case "09":
                idTypeName = "赴台通行证";
                break;
            case "10":
                idTypeName = "港澳通行证";
                break;
            case "15":
                idTypeName = "士兵证";
                break;
            case "21":
                idTypeName = "外国护照";
                break;
            case "22":
                idTypeName = "旅行证";
                break;
            case "23":
                idTypeName = "回乡证";
                break;
            case "24":
                idTypeName = "居留证件";
                break;
            case "25":
                idTypeName = "港澳居民来往内地通行证";
                break;
            case "26":
                idTypeName = "台湾居民来往内地通行证";
                break;
            case "29":
                idTypeName = "其他个人证件";
                break;
            case "31":
                idTypeName = "组织机构代码证";
                break;
            case "32":
                idTypeName = "工商登记证";
                break;
            case "33":
                idTypeName = "税务登记证";
                break;
            case "34":
                idTypeName = "营业执照";
                break;
            case "37":
                idTypeName = "统一社会信用代码";
                break;
            case "99":
                idTypeName = "其它";
                break;
            default:
        }
        return idTypeName;
    }

    public static InputStream downloadFile(String downloadUrl) throws Exception {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try{
            URL url = new URL(downloadUrl);
            conn = (HttpURLConnection)url.openConnection();
            //设置超时间为60秒
            conn.setConnectTimeout(60*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            inputStream = conn.getInputStream();
            return inputStream;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }finally {
//            conn.disconnect();
        }
    }

    public static byte[] toByteArray(InputStream input) throws Exception {
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024*4];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }finally {
//            output.close();
        }
        return output.toByteArray();
    }

    /**
     * 将InputStream写入本地文件
     * @param destination 写入本地目录
     * @param input 输入流
     * @throws IOException IOException
     */
    public static void writeToLocal(String destination, InputStream input) throws Exception {
        try {
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(destination);
            while ((index = input.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }finally {
//            input.close();
//            downloadFile.close();
        }
    }

    public static boolean getLicense() throws Exception {
        boolean result = false;
        InputStream is = null;
        try {
            // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            is = CommonUtil.class.getClassLoader().getResourceAsStream("/template/license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                is.close();
            }
        }
        return result;
    }

    public static void doc2pdf(InputStream is, OutputStream os) throws Exception {
        if (!getLicense()) {
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            throw new Exception("word转换pdf，验证License不通过");
        }
        try {
            Document doc = new Document(is); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static void doc2pdf2(String inPath, String outPath) throws Exception {
        if (!getLicense()) {
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            throw new Exception("word转换pdf，验证License不通过");
        }
        FileOutputStream os = null;
        try {
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }finally {
            if(os != null){
                os.close();
            }
        }
    }


}
