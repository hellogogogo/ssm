package Thread.BankDemo;

// 线程运行类
public class AccountThread implements Runnable {
    // 线程共享一个账户
    private Account account;
    // 取钱的数目
    private int money;

    public AccountThread(Account account, int money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        while (true){
            // 开始取钱
            if (account.withdraw(money)) {
                System.out.println(Thread.currentThread().getName() + "取钱" + money + "元成功，剩余" + account.getMoney() + "元。");
            } else {
                System.out.println(Thread.currentThread().getName() + "余额不足");
                break;
            }
        }
    }
}
