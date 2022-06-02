package de.htwberlin.kba.vokabelduell_ui.impl;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class VokabelduellView {

    public void printMessage(String message) {
        System.out.println (message);
    }

    public int userInputInt() {
        Scanner scanner = new Scanner (System.in);
        return scanner.nextInt();
    }

    public String userInputString() {
        Scanner scanner = new Scanner (System.in);
        return scanner.nextLine();
    }

    public Long userInputLong() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLong();
    }
}
