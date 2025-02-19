package helpers;

import java.util.Random;

public class MyBitGame {

    public static void main(String[] args) {
        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);

            String binaryNumber = Integer.toBinaryString(new Random().nextInt(50)); // Example binary number

            System.out.println("Guess the decimal value of the binary number: " + binaryNumber);

            int userGuess = scanner.nextInt();

            int decimalValue = Integer.parseInt(binaryNumber, 2);

            if (userGuess == decimalValue) {
                System.out.println("Correct! The decimal value of " + binaryNumber + " is " + decimalValue);
            } else {
                System.out.println("Incorrect. The decimal value of " + binaryNumber + " is " + decimalValue);
            }
        }
    }
}
