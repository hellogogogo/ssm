package lytest1911.Thread;

public class AnonymousClass{

    public static void main(String[] args) {
        AnonymousClass testRunnable = new AnonymousClass();
        //匿名内部类
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i=0;i<20;i++){
//                    System.out.println("我在coding");
//                }
//            }
//        }).start();

        //Lambda表达式1
        new Thread(()-> {
            for(int i=0;i<20;i++){
                    System.out.println("我在coding");
                }
            }
        ).start();

        //Lambda表达式2
//        Thread a = new Thread(()->{
//            for(int i=0;i<20;i++){
//                System.out.println("我在coding");
//            }
//        });
//        a.start();

//        Runnable runnable = ()->{
//            for(int i=0;i<20;i++){
//                System.out.println("我在coding");
//            }
//        };
//        new Thread(runnable).start();


        for(int i=0;i<20;i++){
            System.out.println("我在听歌");
            Thread.yield();
        }
    }
}
