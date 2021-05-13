package Thread; /**
 * @Author: liuyu
 * @Date: 2021/5/8 15:15
 */

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LyTest {


    public static void main(String[] args) throws InterruptedException {

    }

    /**
     * 集合1
     */
    @Test
    public void TestList1() throws InterruptedException {
        List<String> list = new ArrayList();
        for(int i=0;i<10000;i++){
            new Thread(()->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
//                System.out.println("线程数："+Thread.activeCount());
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }

    /**
     * 集合2 juc线程安全的集合 CopyOnWrite
     */
    @Test
    public void TestList2() throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        for(int i=0;i<10000;i++){
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }

    /**
     * common-io 下载文件
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        File file = new File("C:/Users/Ly/Desktop/test_1.png");
        FileUtils.copyURLToFile(new URL("http://56.50.32.189:8088/uploads/image/2021-05-10/1620628454877863f272abd1dcde66786b208111f2ad2.jpg"),file);
        System.out.println("下载成功");
    }


}
