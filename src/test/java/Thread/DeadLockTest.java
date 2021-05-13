package Thread; /**
 * @Author: liuyu
 * @Date: 2021/5/12
 */

import lombok.SneakyThrows;

/**
 * 2个线程互相抱着对方的资源，行程死锁
 */
public class DeadLockTest{

    public static void main(String[] args) {
        Makeup makeup = new Makeup(0,"小红");
        Makeup makeup2 = new Makeup(2,"小花~");
        makeup.start();
        makeup2.start();
    }
}

class Lipstick{

}

class Mirror{

}

class Makeup extends Thread{
    int choose ;
    String girlName;

    public Makeup(int choose,String girlName){
        this.choose = choose;
        this.girlName = girlName;
    }

    @SneakyThrows
    @Override
    public void run(){
        this.goMakeup();
    }

    public void goMakeup() throws InterruptedException {
        if(choose == 0){//口红
            synchronized (Lipstick.class){
                System.out.println(girlName+"在用【口红】");
                Thread.sleep(1000);
            }
            synchronized (Mirror.class){
                System.out.println(girlName+"在用【口红】");
            }

        }else {//镜子
            synchronized (Mirror.class){
                System.out.println(girlName+"在用【镜子】");
                Thread.sleep(2000);
            }
            synchronized (Lipstick.class){
                System.out.println(girlName+"在用【口红】");
            }

        }
    }
}