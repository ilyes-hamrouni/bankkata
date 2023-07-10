public class Operation {
    private String type ;
    private Statement statement;

    public Operation(String type, Statement statement) {
        this.type = type;
        this.statement = statement;
    }

    public void printOperation() {
        System.out.println("Operation Type: "+ type);
        this.statement.printStatement();
        System.out.println();
    }

    public String getType() {
        return type;
    }

    public Statement getStatement() {
        return statement;
    }
}
