import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CashSettlementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashSettlementApplication.class, args);
    }
}

@RestController
class UserController {
    private final Map<String, UserData> users = new HashMap<>();

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody UserData userData) {
        String username = userData.getUsername();
        if (users.containsKey(username)) {
            return Map.of("error", "Username already exists");
        }

        String hashedPassword = BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt());
        users.put(username, new UserData(username, hashedPassword));

        return Map.of("message", "User created successfully");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserData userData) {
        String username = userData.getUsername();
        String password = userData.getPassword();

        if (users.containsKey(username) && BCrypt.checkpw(password, users.get(username).getPassword())) {
            return Map.of("message", "Login successful");
        } else {
            return Map.of("error", "Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public Map<String, String> logout() {
        // Implement session management or token invalidation logic here
        return Map.of("message", "Logout successful");
    }
}

@RestController
class TransactionController {
    @PostMapping("/borrow")
    public Map<String, String> borrow(@RequestBody TransactionData transactionData) {
        // Implement borrowing logic here
        return Map.of("message", "Transaction recorded successfully");
    }

    @PostMapping("/lend")
    public Map<String, String> lend(@RequestBody TransactionData transactionData) {
        // Implement lending logic here
        return Map.of("message", "Transaction recorded successfully");
    }

    @GetMapping("/report")
    public Map<String, Object> report(@RequestParam String sortParameter, @RequestParam String sortOrder) {
        // Implement reporting logic here
        return Map.of("report", "Generated report data");
    }
}
