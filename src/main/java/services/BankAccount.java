package services;

import constants.OperationType;
import exceptions.BalanceNotSufficientException;
import models.Operation;
import models.Statement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static constants.OperationType.*;

public class BankAccount {
    private Double balance = 0.00;
    private final List<Operation> operations = new ArrayList<>();

    public Double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public BankAccount(Double balance) {
        this.balance = balance;
        recordOperation(OPENING, LocalDate.now(), balance);
    }

    public void deposit(Double amount) {
        updateBalance(DEPOSIT, amount);
        System.out.println("balance: " + balance);
        recordOperation(DEPOSIT, LocalDate.now(), amount);
    }

    public void deposit(Double amount, LocalDate date) {
        updateBalance(DEPOSIT, amount);
        recordOperation(DEPOSIT, date, amount);
    }

    public void withdraw(Double amount) {
        OperationType operationType = WITHDRAW;
        try {
            updateBalance(operationType, amount);
        } catch (BalanceNotSufficientException e) {
            //logs exceptions in the logs, todo
            operationType = REFUSED;

        } finally {
            recordOperation(operationType, LocalDate.now(), amount);

        }
    }

    public void withdraw(Double amount, LocalDate date) {
        OperationType operationType = WITHDRAW;
        try {
            updateBalance(WITHDRAW, amount);
        } catch (BalanceNotSufficientException e) {
            //logs exceptions in the logs, todo
            operationType = REFUSED;

        } finally {
            recordOperation(operationType, date, amount);

        }
    }

    private void updateBalance(OperationType operationType, Double amount) {
        switch (operationType) {
            case DEPOSIT:
                System.out.println("deposit");
                this.balance = this.balance + amount;
                System.out.println("balance: " + balance);
                break;
            case WITHDRAW:
                if (amount < balance) {
                    balance -= amount;
                } else {
                    throw new BalanceNotSufficientException("Cannot withdraw balance is not sufficient");

                }
                break;
            default:
                System.out.println();
                break;
        }

    }

    private void recordOperation(OperationType operationType, LocalDate date, Double amount) {
        operations.add(Operation
                .builder()
                .type(operationType)
                .statement(Statement
                        .builder()
                        .date(date)
                        .amount(amount)
                        .balance(balance)
                        .build())
                .build());
    }

    public void printOperations() {
        operations.forEach(Operation::printOperation);
    }
}