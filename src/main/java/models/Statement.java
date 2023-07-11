package models;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Getter
@Builder
@Slf4j
public class Statement {

    private LocalDate date;
    private Double amount;
    private Double balance;

    public void printStatement() {
        log.info("Statement: \n Operation Date {}",date);
        log.info("Amount: %d | Balance: %d ", amount, balance);
    }
}