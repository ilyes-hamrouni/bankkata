package services;

import constants.OperationType;
import exceptions.BalanceNotSufficientException;
import exceptions.OperationTypeException;
import lombok.extern.slf4j.Slf4j;
import models.Operation;
import models.Statement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static constants.OperationType.*;

@Slf4j
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
                this.balance = this.balance + amount;
                break;
            case WITHDRAW:
                if (amount > balance) {
                    throw new BalanceNotSufficientException("Cannot withdraw balance is not sufficient");
                }
                 balance -= amount;
                break;
            default:
                throw new OperationTypeException("No matching operation found.");

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