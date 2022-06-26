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
        Scanner scanner = new Scanner (System.in);
        int input = 0;

        while (true) {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Bitte gib eine Zahl zwischen 1 und 4 ein. Drücke enter um die Auswahl zu verlassen.");
                if (scanner.next().isEmpty()) {
                    break;
                }
            }
        }
        return input;
    }

    public String userInputString() throws InputMismatchException {
        Scanner scanner = new Scanner (System.in);
        String input = null;

        while (true) {
            try {
                input = scanner.nextLine();
            } catch (InputMismatchException exception) {
                System.out.println("Bitte gib einen Test ein. Drücke enter um die Auswahl zu verlassen.");
                if (scanner.next().isEmpty()) {
                    break;
                }
            }
        }
        return input;
    }
}
