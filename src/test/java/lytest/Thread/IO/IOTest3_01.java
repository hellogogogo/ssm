package lytest.Thread.IO;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 流练习 FileWriter(全字符操作)
 */
public class IOTest3_01 {

    public static void main(String[] args) throws IOException {
        FileWriter wr = null;
        try {
            String templatePath = IOTest3_01.class.getResource("/template").getPath();
            File file = new File(templatePath+"/textWriter.txt");
            wr = new FileWriter(file);
            String aaa = "IO IS SO EASY!!!!";
            wr.append(aaa);
            wr.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭原则 先使用的后关闭
            if(wr != null){
                wr.close();
            }
        }
    }
}
