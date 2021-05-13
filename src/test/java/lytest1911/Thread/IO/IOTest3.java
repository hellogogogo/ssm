package lytest1911.Thread.IO;


import java.io.*;

/**
 * 流练习 FileOutputStream （适合所有类型文件）
 */
public class IOTest3 {

    public static void main(String[] args) throws IOException {
        OutputStream os = null;
        try {
            String templatePath = IOTest3.class.getResource("/template").getPath();
            File file = new File(templatePath+"/textWriter.txt");
            os = new FileOutputStream(file);
            String text = "io is so easy是";
            byte[] bytes = text.getBytes();//字符串 --> 字节数组 （编码）
            os.write(bytes);
            os.flush();//强制刷新缓冲区
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                os.close();
            }
        }
    }
}
