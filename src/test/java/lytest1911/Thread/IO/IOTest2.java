package lytest1911.Thread.IO;


import java.io.*;

/**
 * 流练习 FileInputStream
 */
public class IOTest2 {

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        try {
            String templatePath = IOTest2.class.getResource("/template").getPath();
            File file = new File(templatePath+"/text.txt");
            is = new FileInputStream(file);
            byte[] buffer = new byte[3];
            int temp ;
            while ((temp = is.read(buffer))!=-1){
                String aaa = new String(buffer,0,temp);
                System.out.println(aaa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                is.close();
            }
        }
    }
}
