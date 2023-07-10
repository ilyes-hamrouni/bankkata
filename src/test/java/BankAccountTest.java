import DAOs.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BankAccountTest {

    public BankAccount account;
    private double initialDeposit = 1000.00;
    @BeforeEach
    public void init() {
        account = new BankAccount(initialDeposit);
    }
    @Test
   public void  depositTest() {
       account.deposit(500.00);
       Assertions.assertEquals(account.getBalance().doubleValue(), 1500.00);

       account.deposit(200.00,new Date("10/10/2005"));
       Assertions.assertEquals(account.getOperations().size(),3);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getAmount(),200.00);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getBalance().doubleValue(),1700.00);
        Assertions.assertEquals(account.getOperations().get(2).getType(),"Deposit");
       Assertions.assertEquals(account.getOperations().get(2).getStatement().getDate(),new Date("10/10/2005"));

   }


   @Test
    public void withdrawTest() {
        account.withdraw(300.00);
        Assertions.assertEquals(account.getBalance().doubleValue(),700.00);

       account.withdraw(200.00,new Date("10/10/2005"));
       Assertions.assertEquals(account.getOperations().size(),3);
       Assertions.assertEquals(account.getOperations().get(2).getStatement().getAmount(),200.00);
       Assertions.assertEquals(account.getOperations().get(2).getStatement().getBalance().doubleValue(),500.00);

       Assertions.assertEquals(account.getOperations().get(2).getType(),"Withdraw");
       Assertions.assertEquals(account.getOperations().get(2).getStatement().getDate(),new Date("10/10/2005"));
   }

    @Test
    public void withdrawRefusedTest() {
        account.withdraw(1300.00);
        Assertions.assertEquals(account.getBalance().doubleValue(),1000.00);
    }

}
