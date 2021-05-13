package Thread; /**
 * @Author: liuyu
 * @Date: 2021/5/12
 */

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafe{

    public static void main(String[] args) {
//        BuyTicket t1 = new BuyTicket();
//        new Thread(t1).start();
//        new Thread(t1).start();
//        new Thread(t1).start();

        System.out.println("*************************");
        BuyTicket2 t2 = new BuyTicket2();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
    }
}

/**
 * synchronized实现
 */
class BuyTicket implements Runnable{
    volatile int ticketNum = 10;
    boolean flag = true;

    @SneakyThrows
    @Override
    public void run() {
        while (flag){
            this.buy();
        }
    }
    private synchronized void buy() throws InterruptedException {
        if(ticketNum<=0){
            flag = false;
            System.out.println(Thread.currentThread().getName()+"卖完了！！");
            return;
        }
        //延迟
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ --ticketNum);
    }
}

/**
 * ReentrantLock实现
 */
class BuyTicket2 implements Runnable{
    volatile int ticketNum = 10;
    boolean flag = true;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @SneakyThrows
    @Override
    public void run() {
        while (flag){
            this.buy();
        }
    }
    private void buy() throws InterruptedException {
        try{
            lock.lock();
            if(ticketNum<=0){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"卖完了！！");
                return;
            }
            //延迟
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"，抢到了票!"+"剩余票数："+ --ticketNum);
        }finally {
            lock.unlock();
        }

    }
}