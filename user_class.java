import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String email;

    public User(String name, String email) {
        this.userId = UUID.randomUUID().toString(); // Unique ID
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
