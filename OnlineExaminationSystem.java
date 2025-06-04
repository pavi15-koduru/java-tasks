import java.util.*;
public class OnlineExaminationSystem {
    static Scanner scanner = new Scanner(System.in);
    static String username = "user";
    static String password = "pass";
    static boolean loggedIn = false;

    static String[] questions = {
        "Q1. What is the capital of France?\nA. Berlin\nB. Paris\nC. Rome\nD. Madrid",
        "Q2. Java is a ___ language?\nA. Low-level\nB. Machine\nC. High-level\nD. None",
        "Q3. Who developed Java?\nA. James Gosling\nB. Dennis Ritchie\nC. Bjarne Stroustrup\nD. Guido van Rossum"
    };

    static char[] answers = {'B', 'C', 'A'};
    static char[] userAnswers = new char[3];

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Online Examination System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1 && login()) {
                showMenu();
            } else if (choice == 2) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid login or choice.");
            }
        }
    }

    static boolean login() {
        System.out.print("Enter Username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String inputPass = scanner.nextLine();

        if (inputUser.equals(username) && inputPass.equals(password)) {
            loggedIn = true;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    static void showMenu() {
        while (loggedIn) {
            System.out.println("\n-- User Menu --");
            System.out.println("1. Update Profile & Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> updateProfile();
                case 2 -> startExam();
                case 3 -> {
                    logout();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void updateProfile() {
        System.out.print("Enter new username: ");
        username = scanner.nextLine();
        System.out.print("Enter new password: ");
        password = scanner.nextLine();
        System.out.println("Profile updated successfully!");
    }

    static void startExam() {
        System.out.println("\nExam started. You have 60 seconds.");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Auto-submitting your answers.");
                submitExam();
                timer.cancel();
            }
        }, 60000); // 60 seconds

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n" + questions[i]);
            System.out.print("Enter your answer (A/B/C/D): ");
            userAnswers[i] = scanner.nextLine().toUpperCase().charAt(0);
        }

        submitExam();
        timer.cancel(); // Cancel timer if user submits early
    }

    static void submitExam() {
        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (userAnswers[i] == answers[i]) {
                score++;
            }
        }
        System.out.println("Exam submitted successfully!");
        System.out.println("Your Score: " + score + "/" + answers.length);
    }

    static void logout() {
        loggedIn = false;
        System.out.println("You have been logged out.");
    }
}
