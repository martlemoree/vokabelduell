package de.htwberlin.kba.vokabelduell_ui.impl;

import org.springframework.stereotype.Component;

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
        return scanner.nextInt();
    }

    public Long userInputLong() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void byeBye() {
        System.out.println("Bye :)");
    }
}
