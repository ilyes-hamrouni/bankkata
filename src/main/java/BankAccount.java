import Exceptions.BalanceNotSufficientException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    private final String WITHDRAW = "Withdraw";
    private final String DEPOSIT = "Deposit";
    private final String OPENING = "Account Opening";
    private final String REFUSED = "Operation Refused";
    private Double balance=0.00;
    private final List<Operation> operations = new ArrayList<>();

    public Double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public BankAccount(Double balance) {
        this.balance = balance;
        this.recordOperation(OPENING,new Date(),balance,this.balance);
    }

    public void deposit(Double amount) {
        this.updateBalance(DEPOSIT,amount);
        this.recordOperation(DEPOSIT,new Date(),amount,balance);
    }

    public void deposit(Double amount, Date date) {
        this.updateBalance(DEPOSIT,amount);
        this.recordOperation(DEPOSIT,date,amount, balance);
    }

    public void withdraw(Double amount) {
        String operationType = WITHDRAW;
        try {
            this.updateBalance(operationType,amount);
        } catch (BalanceNotSufficientException e) {
            //logs exceptions in the logs, todo
            operationType = REFUSED;

        } finally {
            this.recordOperation(operationType, new Date(), amount, balance);

        }
    }
    public void withdraw(Double amount, Date date) {
        String operationType = WITHDRAW;
        try {
            this.updateBalance(WITHDRAW,amount);
        } catch (BalanceNotSufficientException e) {
            //logs exceptions in the logs, todo
            operationType = REFUSED;

        } finally {
            this.recordOperation(operationType, date, amount, balance);

        }
    }

    private void  updateBalance(String operationType, Double amount) {
        if(DEPOSIT.equals(operationType)) {
            this.balance = this.balance + amount;
        }

        if(WITHDRAW.equals(operationType) && amount < balance ){
            this.balance = this.balance - amount;
        }
        if(WITHDRAW.equals(operationType) && amount > balance) {
            throw new BalanceNotSufficientException("Cannot withdraw balance is not sufficient");
        }
    }
    private void recordOperation(String operationType, Date date, Double amount, Double Balance ) {
        this.operations.add(new Operation(operationType,new Statement(date,amount,balance)));
    }

    public void printOperations() {
        this.operations.forEach(Operation::printOperation);
    }
}