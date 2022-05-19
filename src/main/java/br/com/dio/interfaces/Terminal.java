package br.com.dio.interfaces;

import br.com.dio.templates.UserInterfaceTemplate;

import java.io.IOException;
import java.util.Scanner;

public class Terminal implements UserInterfaceTemplate {
    private static Terminal terminalInstance;
    private final Scanner input;

    private Terminal() {
        input = new Scanner(System.in);
    }

    public static Terminal getTerminalInstance() {
        if (terminalInstance == null) terminalInstance = new Terminal();
        return terminalInstance;
    }

    @Override
    public void showMainMenu() {
        System.out.println("-----Welcome to the MacBank!-----");
        System.out.println("1 - Create checking account");
        System.out.println("2 - Create savings account");
        System.out.println("3 - Show account balance");
        System.out.println("4 - Deposit funds");
        System.out.println("5 - Withdraw funds");
        System.out.println("6 - Transfer funds");
        System.out.println("7 - Delete account");
        System.out.println("0 - Quit");
        System.out.println("---------------------------------");
    }

    @Override
    public String getUserInfo(String instruction) {
        System.out.print(instruction);
        return input.next();
    }

    @Override
    public void showMessage(String messageToShow) {
        System.out.println(messageToShow);
    }

    @Override
    public void actionToContinue() {
        System.out.print("Press any key to continue...");
        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }

}
