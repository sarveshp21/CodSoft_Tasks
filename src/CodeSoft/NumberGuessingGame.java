package CodeSoft;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        int lowerBound = 1;
        int upperBound = 100;
        int numberOfAttempts = 5;
        int score = 0;

        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int currentAttempt = 0;

            System.out.println("\nGuess the number between " + lowerBound + " and " + upperBound + ".");
            
            while (currentAttempt < numberOfAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                currentAttempt++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + currentAttempt + " attempts.");
                    score += numberOfAttempts - currentAttempt + 1;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                if (currentAttempt == numberOfAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();

            if (!playAgainInput.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame over. Your total score is: " + score);
        scanner.close();
    }
}
