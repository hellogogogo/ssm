package Thread; /**
 * @Author: liuyu
 * @Date: 2021/5/11 15:15
 */

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallAble implements Callable{

    static volatile int ticketNum = 100;

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
        System.out.println("线程数:"+Thread.activeCount());
        //提交执行
        //实现1 Future：有返回值（callable）
        Future<Boolean> r1 = executorService.submit(testCallAble1);
        Future<Boolean> r2 = executorService.submit(testCallAble1);
        Future<Boolean> r3 = executorService.submit(testCallAble1);
        //实现2 execute：无返回值（runnable）
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("方法2");
            }
        });
        //获取结果
        System.out.println("testCallAble1，结果："+r1.get());
        System.out.println("testCallAble1，结果："+r2.get());
        System.out.println("testCallAble1，结果："+r3.get());
        //关闭服务
        executorService.shutdown();
        System.out.println("线程数:"+Thread.activeCount());
    }

    @Override
    public Object call() throws Exception {
        while (ticketNum>0){
            buyTicket();
        }
        return true;
    }

    private synchronized void buyTicket() throws InterruptedException {
        if(ticketNum<=0){
            System.out.println(Thread.currentThread().getName() + "卖完了！");
            return;
        }
        System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ --ticketNum);
        Thread.yield();
    }
}
