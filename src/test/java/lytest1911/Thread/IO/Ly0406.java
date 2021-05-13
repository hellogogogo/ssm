package lytest1911.Thread.IO;


import java.io.*;

/**
 * 流练习 FileInputStream
 */
public class Ly0406 {

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        try {
            String templatePath = Ly0406.class.getResource("/template").getPath();
            File file = new File(templatePath+"/text.txt");
            is = new FileInputStream(file);
            int temp ;
            while ((temp = is.read())!=-1){
                System.out.println((char)temp);
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
