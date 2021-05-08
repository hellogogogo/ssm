/**
 * @Author: liuyu
 * @Date: 2021/5/8 15:15
 */
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class LyTest {

    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i=0;i<3;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
//                    synchronized (LyTest.class){
                        System.out.println("***************************"+Thread.currentThread().getName()+"开始："+num);
                        while (num.get()<1000){
                            System.out.println(Thread.currentThread().getName()+" : "+num.incrementAndGet());
                        }
                        System.out.println("***************************"+Thread.currentThread().getName()+"结束："+num);
//                    }
                }
            });
            t.start();
        }
    }

    @Test
    public void test()  {

    }
}
