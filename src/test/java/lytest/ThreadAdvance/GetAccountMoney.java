package lytest.ThreadAdvance;

public class GetAccountMoney{

    public static void main(String[] args) {
        Account account = new Account(100,"小y");
        Withdraw you = new Withdraw(account,50,"你");
        Withdraw wife = new Withdraw(account,90,"老婆");
        you.start();
        wife.start();
    }
}

class Account {
    int money;//金额
    String name;//名称
    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Withdraw extends Thread{
    Account account;//取钱的账户
    int drawMoney;//取钱数
    int packetTotal;//取钱总数

    public Withdraw(Account account, int drawMoney, String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    @Override
    public void run() {
        //节省资源
        if(account.money-drawMoney==0){
            return;
        }
        synchronized (account){
            if(account.money-drawMoney<=0){
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawMoney;
            packetTotal += drawMoney;
            System.out.println(this.getName()+"账户余额为："+account.money);
            System.out.println(this.getName()+"口袋的钱为："+packetTotal);
        }
    }

}
