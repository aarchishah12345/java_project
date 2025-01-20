import java.util.List;

public class Expense {
    private String expenseId;
    private String title;
    private double amount;
    private User payer;
    private List<User> participants;

    public Expense(String title, double amount, User payer, List<User> participants) {
        this.expenseId = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.amount = amount;
        this.payer = payer;
        this.participants = participants;
    }

    public double calculateSplit() {
        return amount / participants.size();
    }

    public void displayExpense() {
        System.out.println("Expense: " + title + " | Amount: " + amount + " | Payer: " + payer.getName());
        System.out.println("Split Amount: " + calculateSplit());
        for (User user : participants) {
            System.out.println("Participant: " + user.getName());
        }
    }
}
