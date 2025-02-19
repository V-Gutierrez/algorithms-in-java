package helpers;

import java.util.Scanner;

public class TextToBinary {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nDigite um texto: ");
            String text = scanner.nextLine();

            // Convertendo para bytes
            byte[] bytes = text.getBytes();

            System.out.println("Texto: " + text);
            System.out.print("Binário: ");

            for (byte b : bytes) {
                System.out.print(Integer.toBinaryString(b & 0xFF) + " ");
            }
        }
    }
}
