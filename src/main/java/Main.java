import DAOs.BankAccount;

import java.util.Date;

public class Main {

        public static void main(String[] args) {
            BankAccount account = new BankAccount(1000.00);

            account.deposit(500.00);
            account.withdraw(300.00);
            account.withdraw(2000.00, new Date("10/09/2023"));

            account.printOperations();
        }


}
