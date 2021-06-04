package 线程池0526;

public class Task implements Runnable{
    private int nov;

    public Task(int nov){
        this.nov=nov;
    }
    public void run(){
        // System.out.println("执行当前任务的线程是:"+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            
        }
        System.out.println("我是任务："+nov+"我在执行...");
    }
}