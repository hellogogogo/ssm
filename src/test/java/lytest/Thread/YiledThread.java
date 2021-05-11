package lytest.Thread;

public class YiledThread implements Runnable{
    public void run(){
            for(int i=0;i<20;i++){
                System.out.println("我在coding");
            }
        }

    public static void main(String[] args) {
        YiledThread testRunnable = new YiledThread();
        Thread thread = new Thread(testRunnable);
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("我在听歌");
            Thread.yield();//让线程重新回到就绪状态
        }
    }
}
