package lytest1911.Thread;

public class JoinThread implements Runnable{
    public void run(){
            for(int i=0;i<20;i++){
                System.out.println("我在coding");
            }
        }

    public static void main(String[] args) throws InterruptedException {
        JoinThread testRunnable = new JoinThread();
        Thread thread = new Thread(testRunnable);
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("我在听歌");
            if(i==1){
                System.out.println("i="+i+"main被阻塞了");
                thread.join();//main被阻塞了
            }
        }
    }
}
