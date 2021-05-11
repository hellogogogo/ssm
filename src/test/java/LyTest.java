/**
 * @Author: liuyu
 * @Date: 2021/5/8 15:15
 */
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class LyTest {

//    static AtomicInteger num = new AtomicInteger(0);
    static volatile int num = 0;

    public static void main(String[] args) {
        System.out.println("线程数1："+Thread.activeCount());
//        Thread.currentThread().getThreadGroup().list();//打印当前线程组
        for (int i=0;i<3;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
//                    synchronized (LyTest.class){
                        System.out.println("***************************"+Thread.currentThread().getName()+"开始："+num);
                        while (num<1000){
                            System.out.println(Thread.currentThread().getName()+" : "+num++);
                        }
                        System.out.println("***************************"+Thread.currentThread().getName()+"结束："+num);
//                    }
                }
            });
            t.start();
        }
        if(Thread.activeCount()>2){
            Thread.yield();//让当前线程回到就绪状态
        }
        System.out.println("线程数2："+Thread.activeCount());
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

    volatile int ticketNum = 100;
    /**
     * 线程安全问题 Runnable实现
     */
    @Test
    public void test3() throws Exception {
        for(int i=0;i<3;i++){
            new Thread(()->{
                while (ticketNum>0){
                    ticketNum --;
                    System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ticketNum);
                }
            }).start();
        }
        Thread.currentThread().setName("小明");
        if(Thread.activeCount() == 2){
            Thread.yield();
        }
        while (ticketNum>0){
            ticketNum --;
            System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ticketNum);
        }
        System.out.println("卖完了！！");

    }

}
