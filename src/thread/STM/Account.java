package thread.STM;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 通过STM实现事务操作
 */
public class Account {
    //余额
    private TxnRef<Integer> balance;

    //构造⽅法
    public Account(int balance) {
        this.balance = new TxnRef<Integer>(balance);
    }

    //转账操作
    public void transfer(Account target, int amt) {
        STM.atomic((txn) -> {
            Integer from = balance.getValue(txn);
            balance.setValue(from - amt, txn);
            Integer to = target.balance.getValue(txn);
            target.balance.setValue(to + amt, txn);
        });
    }
}
