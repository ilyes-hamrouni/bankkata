import constants.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BankAccount;

import java.time.LocalDate;

public class BankAccountTest {

    public BankAccount account;
    private final double initialDeposit = 1000.00;

    @BeforeEach
    public void init() {
        account = new BankAccount(initialDeposit);
    }

    @Test
    public void depositTest() {
        // basic deposit scenario
        account.deposit(500.00);
        Assertions.assertEquals(account.getBalance().doubleValue(), 1500.00);
        // deposit test with date
        account.deposit(200.00, LocalDate.of(2005, 10, 10));
        // assert balance is updated and operations are recorded.
        Assertions.assertEquals(account.getOperations().size(), 3);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getAmount().doubleValue(), 200.00);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getBalance().doubleValue(), 1700.00);
        Assertions.assertEquals(account.getOperations().get(2).getType(), OperationType.DEPOSIT);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getDate(), LocalDate.of(2005, 10, 10));

    }


    @Test
    public void withdrawTest() {
        account.withdraw(300.00);
        Assertions.assertEquals(account.getBalance().doubleValue(), 700.00);

        account.withdraw(200.00, LocalDate.of(2005, 10, 10));
        Assertions.assertEquals(account.getOperations().size(), 3);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getAmount().doubleValue(), 200.00);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getBalance().doubleValue(), 500.00);

        Assertions.assertEquals(account.getOperations().get(2).getType(), OperationType.WITHDRAW);
        Assertions.assertEquals(account.getOperations().get(2).getStatement().getDate(), LocalDate.of(2005, 10, 10));
    }

    @Test
    public void withdrawRefusedTest() {
        account.withdraw(1300.00);
        Assertions.assertEquals(account.getBalance().doubleValue(), 1000.00);
    }

}
