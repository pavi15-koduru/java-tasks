import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int numberOfTries = 0;
        int maxTries = 10;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between " + lowerBound + " and " + upperBound + ".");

        while (numberOfTries < maxTries) {
            System.out.print("Enter your guess: ");
            int userGuess;

            // Check if input is an integer
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                numberOfTries++;

                if (userGuess == numberToGuess) {
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // clear the invalid input
            }
        }

        if (hasGuessedCorrectly) {
            System.out.println("Congratulations! You've guessed the number in " + numberOfTries + " tries.");
        } else {
            System.out.println("Sorry! You've used all your attempts. The number was: " + numberToGuess);
        }

        scanner.close();
    }
}
