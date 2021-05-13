package Thread.BankDemo;

public class Test01 {
    public static void main(String[] args) {
        // 创建银行账户类
        Account account = new Account(1001, "张三", 1000);

        // 创建两个线程，共享一个账户
        AccountThread at1 = new AccountThread(account, 100);
        AccountThread at2 = new AccountThread(account, 100);

        Thread t1 = new Thread(at1);
        Thread t2 = new Thread(at2);
        // 启动线程，开始取钱
        t1.start();
        t2.start();
    }

}
