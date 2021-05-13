package lytest1911.ThreadAdvance;

public class TestRunnableBuyTicket implements Runnable{

    Integer ticket = 10;

    public void run(){
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(ticket<0){
                break;
            }
            synchronized (ticket){
                if(ticket>0){
                    System.out.println(Thread.currentThread().getName()+"-->"+--ticket);
                }else{
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        TestRunnableBuyTicket testRunnableBuyTicket = new TestRunnableBuyTicket();
        new Thread(testRunnableBuyTicket,"小m").start();
        new Thread(testRunnableBuyTicket,"小y").start();
        new Thread(testRunnableBuyTicket,"小q").start();
    }
}
