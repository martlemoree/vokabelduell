package de.htwberlin.kba.vokabelduell_ui.impl;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class VokabelduellView {

    public void printMessage(String message) {
        System.out.println (message);
    }

    public int userInputInt() throws InputMismatchException {
        int input = 0;
        boolean rightInput = true;
        Scanner scanner = null;

        while (rightInput) {
            try {
                scanner = new Scanner (System.in);
                input = scanner.nextInt();
                rightInput = false;
            } catch (InputMismatchException exception) {
                System.out.println("Bitte gib eine Zahl entsprechend den Zahlen des Menüs ein. Drücke enter um die Auswahl zu verlassen.");
                if (scanner.next().isEmpty()) {
                    break;
                }
            }
        }
        return input;
    }

    public String userInputString() throws InputMismatchException {
        String input = null;
        boolean rightInput = true;
        Scanner scanner = null;

        while (rightInput) {
            try {
                scanner = new Scanner (System.in);
                input = scanner.nextLine();
                rightInput = false;
            } catch (InputMismatchException exception) {
                System.out.println("Bitte gib einen Text ein. Drücke enter um die Auswahl zu verlassen.");
                if (scanner.next().isEmpty()) {
                    break;
                }
            }
        }
        return input;
    }

    public long userInputLong() {

        Long input = 0L;
        boolean rightInput = true;
        Scanner scanner = null;

        while (rightInput) {
            try {
                scanner = new Scanner(System.in);
                input = scanner.nextLong();
                rightInput = false;

            } catch (Exception e) {
                System.out.println("Bitte gib einen Text ein. Drücke enter um die Auswahl zu verlassen.");
                if (scanner.next().isEmpty()) {
                    break;
                }
            }

        }
        return input;
    }
}
