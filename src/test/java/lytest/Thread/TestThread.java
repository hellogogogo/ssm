package lytest.Thread;

public class TestThread extends Thread{

    @Override
    public void run(){
        for(int i=0;i<2000;i++){
            System.out.println("我在coding");
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();

        for(int i=0;i<2000;i++){
            System.out.println("我在听歌1");
        }
    }
}
