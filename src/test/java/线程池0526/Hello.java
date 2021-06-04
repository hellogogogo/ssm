package 线程池0526;

/**
 * Hello
 */
public class Hello {
    public static void main(String[] args) {

        //ult是用户线程（应用线程）
        //jvm虚拟机基本上用的klt模型（内核线程）   
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        System.out.println("1231");
    }
}