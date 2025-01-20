import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private List<User> users;
    private List<Expense> expenses;

    public Group(String groupName) {
        this.groupId = java.util.UUID.randomUUID().toString();
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void displayGroupSummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        System.out.println("Group Summary for " + groupName + ":");
        for (Expense expense : expenses) {
            System.out.println("- " + expense.getTitle() + ": " + expense.getAmount() + " paid by " + expense.getPayer().getName());
        }
    }

    public User findUserByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
