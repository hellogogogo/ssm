package 线程池0526;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TheadModel
 */
public class TheadModel {

    public static void main(String[] args) {

        //核心线程池大小，最多多少个线程，如果没有工作最长空闲多久，满负荷工作下提供阻塞队列，线程临时工(超出允许线程最大值后的线程会被放在这里)
        //阻塞队列：   
        //   1、不管并发有多高，永远只有一个线程可以进行队列的入队和出队操作 线程安全的队列
        //     有界||无界
        //  队列满了之后 只能进行出队操作，所有入队操作必须等待 也就是被阻塞
        //  队列空只能进行入队操作，所有出队操作必须等待，也就是被阻塞

        /**
public ThreadPoolExecutor(int corePoolSize,             即使空闲时仍保留在池中的线程数，除非设置 allowCoreThreadTimeOut 
                int maximumPoolSize,                    池中允许的最大线程数 
                long keepAliveTime,                     当线程数大于核心时，这是多余的空闲线程在终止之前等待新任务的最大时间。 
                TimeUnit unit,                          keepAliveTime参数的时间单位 
                new ArrayBlockingQueue<Runnable>(5)     在执行任务之前用于保存任务的队列。 该队列将仅保存execute方法提交的Runnable任务。 
                    )
	所有已知实现类： （具体可以到jdk上面看,因为我也不知道区别）
	ArrayBlockingQueue ， DelayQueue ， LinkedBlockingDeque ， LinkedBlockingQueue ， LinkedTransferQueue ， PriorityBlockingQueue ， SynchronousQueue 
         */
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,3,60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new Task(i));
        }
        threadPoolExecutor.shutdown();
    }

}