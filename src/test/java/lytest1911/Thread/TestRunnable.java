package lytest1911.Thread;

public class TestRunnable implements Runnable{
    //定义退出标志，true会一直执行，false会退出循环
    //使用volatile目的是保证可见性，一处修改了标志，处处都要去主存读取新的值，而不是使用缓存
    // wait和notify
    public volatile boolean flag = true;
    int j;

    public void run(){
        while (flag){
            System.out.println("我在coding");
            this.stop();
        }
    }

    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable();
        Thread thread = new Thread(testRunnable);
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("我在听歌1");
        }
    }

    public void stop(){
        System.out.println("——————————————————————————");
        System.out.println("coding线程终止");
        //修改退出标志，使线程终止
        this.flag = false;
    }
}
