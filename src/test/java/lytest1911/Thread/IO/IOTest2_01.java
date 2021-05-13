package lytest1911.Thread.IO;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 流练习 FileReader(全字符操作)
 */
public class IOTest2_01 {

    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        try {
            String templatePath = IOTest2_01.class.getResource("/template").getPath();
            File file = new File(templatePath+"/text.txt");
            fr = new FileReader(file);
            char[] buffer = new char[3];//使用FileReader处理全字符操作的好处，不用byte转String
            int len ;
            while ((len = fr.read(buffer))!=-1){
//                String aaa = new String(buffer);
                String aaa = new String(buffer,0,len);//加上0,len 好习惯
                System.out.println(aaa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭原则 先使用的后关闭
            if(fr != null){
                fr.close();
            }
        }
    }
}
