import java.util.Date;

public class Statement {
    private Date date;
    private double amount;
    private Double balance;


    public Statement(Date date, Double amount, Double balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public void printStatement() {
        System.out.println("Bank Statement:\n Date: "+date+"\n Amount: "+amount+"\n Balance:" +balance);
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Double getBalance() {
        return balance;
    }
}