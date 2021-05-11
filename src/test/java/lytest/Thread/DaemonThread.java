package lytest.Thread;

/**
 * 守护线程
 */
public class DaemonThread implements Runnable{
    public void run(){
            while (true){
                System.out.println("我在coding");
            }
        }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread testRunnable = new DaemonThread();
        Thread thread = new Thread(testRunnable);
        thread.setDaemon(true);
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("我在听歌");
        }
    }
}
