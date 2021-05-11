/**
 * @Author: liuyu
 * @Date: 2021/5/8 15:15
 */

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallAble implements Callable{

    volatile int ticketNum = 100;

    public static void main(String[] args) {

    }

    /**
     * 线程安全问题 Callable实现
     */
    @Test
    public void test1() throws Exception {
        //创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        TestCallAble testCallAble1 = new TestCallAble();
        TestCallAble testCallAble2 = new TestCallAble();
        TestCallAble testCallAble3 = new TestCallAble();
        //提交执行
        Future<Boolean> r1 = executorService.submit(testCallAble1);
        Future<Boolean> r2 = executorService.submit(testCallAble2);
        Future<Boolean> r3 = executorService.submit(testCallAble3);
        //获取结果
        System.out.println("testCallAble1，结果："+r1.get());
        System.out.println("testCallAble1，结果："+r2.get());
        System.out.println("testCallAble1，结果："+r3.get());
        //关闭服务
        executorService.shutdown();
    }

    @Override
    public Object call() throws Exception {
        while (ticketNum>0){
            ticketNum --;
            System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ticketNum);
        }
        return true;
    }
}
