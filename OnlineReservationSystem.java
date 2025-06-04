import java.util.*;

class Reservation {
    String name, from, to, date, classType;
    int trainNo;
    String pnr;

    public Reservation(String name, int trainNo, String from, String to, String date, String classType, String pnr) {
        this.name = name;
        this.trainNo = trainNo;
        this.from = from;
        this.to = to;
        this.date = date;
        this.classType = classType;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Name: " + name + ", Train No: " + trainNo + ", From: " + from + ", To: " + to +
                ", Date: " + date + ", Class: " + classType;
    }
}

public class OnlineReservationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, Reservation> reservations = new HashMap<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        users.put("admin", "1234"); // default user

        System.out.println("Welcome to the Online Reservation System");

        if (login()) {
            boolean running = true;
            while (running) {
                System.out.println("\n1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> makeReservation();
                    case 2 -> cancelReservation();
                    case 3 -> {
                        System.out.println("Thank you for using the system.");
                        running = false;
                    }
                    default -> System.out.println("Invalid option!");
                }
            }
        }
    }

    private static boolean login() {
        System.out.print("Enter login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(loginId) && users.get(loginId).equals(password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }

    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        int trainNo = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter destination: ");
        String to = scanner.nextLine();
        System.out.print("Enter date of journey (dd-mm-yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        String pnr = "PNR" + (10000 + random.nextInt(90000));
        Reservation reservation = new Reservation(name, trainNo, from, to, date, classType, pnr);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    private static void cancelReservation() {
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation Found:");
            System.out.println(reservations.get(pnr));
            System.out.print("Confirm cancellation? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Cancellation successful.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with that PNR.");
        }
    }
}