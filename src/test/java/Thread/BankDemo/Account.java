package Thread.BankDemo;

// 银行账户类
public class Account {
    // 账号
    private long no;

    // 姓名
    private String name;

    // 余额
    private double money;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Account() {

    }

    public Account(long no, String name, double money) {
        this.no = no;
        this.name = name;
        this.money = money;
    }

    // 取钱
    /*
        在实例方法上使用synchronized，锁的一定是this对象。
        这种方式不灵活，另外表示整个方法都需要同步，可能会无故扩大同步的范围。
        导致程序的效率降低。所以这种方式不常用。

        synchronized使用在实例方法上有什么优点？
            就是代码比较少，写一个synchronized关键字就行。

        如果共享的对象就是this，并且需要同步的代码是整个方法体，建议在实例方法上
        添加synchronized关键字修饰，因为需要同步的确实是整个方法体。
     */
    // 也可以在实例方法上，加synchronized，这样就扩大了安全的范围，同样效率就变低了
    public  boolean withdraw(int m) {
        // 以下代码是需要线程排队的
        synchronized (Account.class) {  //必须锁拥有变量的对象
            if (m <= 0 || m > this.money)  return false;
            // 模拟网络延迟
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.money = this.money - m;
            return true;
        }
    }
}
