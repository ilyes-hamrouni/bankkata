package Constants;

public enum OperationType {
    OPENING("Account Opening"),
    REFUSED("Operation Refused"),
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw");

    private String operationType;

    OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return this.operationType;
    }
}
