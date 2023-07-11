package models;

import constants.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class Operation {

    private OperationType type;
    private Statement statement;

    public void printOperation() {
        log.info("Operation Type {}: ", type);
        this.statement.printStatement();
    }
}
