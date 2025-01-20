import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Group group = new Group("Friends Group"); // Create a group
        boolean isRunning = true;

        System.out.println("Welcome to the Expense Splitting Application!");

        while (isRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Show Group Summary");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addUser(scanner, group);
                    case 2 -> addExpense(scanner, group);
                    case 3 -> group.displayGroupSummary();
                    case 4 -> {
                        isRunning = false;
                        System.out.println("Exiting the application. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        scanner.close();
    }

    private static void addUser(Scanner scanner, Group group) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        User user = new User(name, email);
        group.addUser(user);

        System.out.println("User added successfully: " + user.getName() + " (" + user.getEmail() + ")");
    }

    private static void addExpense(Scanner scanner, Group group) {
        if (group.getUsers().isEmpty()) {
            System.out.println("No users in the group. Add users first.");
            return;
        }

        System.out.print("Enter expense title: ");
        String title = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount;

        try {
            amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            System.out.println("Invalid amount. Please try again.");
            scanner.nextLine(); // Clear invalid input
            return;
        }

        System.out.print("Enter payer's name: ");
        String payerName = scanner.nextLine();
        User payer = group.findUserByName(payerName);

        if (payer == null) {
            System.out.println("Payer not found. Please add the user first.");
            return;
        }

        System.out.print("Enter participant names (comma-separated): ");
        String[] participantNames = scanner.nextLine().split(",");
        List<User> participants = new ArrayList<>();

        for (String participantName : participantNames) {
            User participant = group.findUserByName(participantName.trim());
            if (participant != null) {
                participants.add(participant);
            } else {
                System.out.println("Participant " + participantName.trim() + " not found. Skipping...");
            }
        }

        if (participants.isEmpty()) {
            System.out.println("No valid participants found. Expense not added.");
            return;
        }

        System.out.println("Participants included in this expense:");
        participants.forEach(participant -> System.out.println("- " + participant.getName()));

        Expense expense = new Expense(title, amount, payer, participants);
        group.addExpense(expense);

        System.out.println("Expense added successfully!");
    }
}
