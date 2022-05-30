package br.com.dio.utilitaries;

import br.com.dio.bank.Bank;
import br.com.dio.templates.UserInterfaceTemplate;
import lombok.AccessLevel;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ProgramHandler class
 *
 * @author Wagner Maciel
 */
public class ProgramHandler {
    @Setter(AccessLevel.NONE)
    private UserInterfaceTemplate userInterface;
    @Setter(AccessLevel.NONE)
    private Bank myBank;

    public ProgramHandler(UserInterfaceTemplate userInterface) {
        this.userInterface = userInterface;
        myBank = Bank.getBankInstance();
    }

    private void showMainMenu() {
        userInterface.showMainMenu();
    }

    public void programLoop() {
        boolean exitProgram = false;
        do {
            showMainMenu();
            String command = userInterface.getUserInfo("Type the desired operation: ");
            switch (command) {
                case "1":
                    this.createCheckingAccount();
                    break;
                case "2":
                    this.createSavingsAccount();
                    break;
                case "3":
                    this.showAccountBalance();
                    break;
                case "4":
                    this.deposit();
                    break;
                case "5":
                    this.withdraw();
                    break;
                case "6":
                    this.transfer();
                    break;
                case "7":
                    this.deleteAccount();
                    break;
                case "0":
                    exitProgram = true;
                    break;
                default:
                    userInterface.showMessage("Invalid operation!");
                    break;
            }
            if (!exitProgram) userInterface.actionToContinue();
            userInterface.showMessage("");
        } while (!exitProgram);
        this.goodbyeMessage();
    }

    private int getIntFromUser(String instruction) {
        try {
            return Integer.parseInt(userInterface.getUserInfo(instruction));
        } catch (Exception e) {
            throw new RuntimeException("Typed value must be a natural number!");
        }
    }

    private double getDoubleFromUser(String instruction) {
        try {
            return Double.parseDouble(userInterface.getUserInfo(instruction));
        } catch (Exception e) {
            throw new RuntimeException("Typed value must be a number!");
        }
    }

    private String toCurrency(BigDecimal value) {
        if (value == null) throw new RuntimeException("Error processing value!");
        return value.setScale(2, RoundingMode.FLOOR).toPlainString();
    }

    private void createCheckingAccount() {
        String clientName = userInterface.getUserInfo("Client name: ");
        String clientCPF = userInterface.getUserInfo("Client CPF: ");
        String clientPassword = userInterface.getUserInfo("Client password: ");
        try {
            int accountLimit = getIntFromUser("Account limit: ");
            userInterface.showMessage("Account number is " + myBank.createAccount(clientName, clientCPF, clientPassword, accountLimit));
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void createSavingsAccount() {
        String clientName = userInterface.getUserInfo("Client name: ");
        String clientCPF = userInterface.getUserInfo("Client CPF: ");
        String clientPassword = userInterface.getUserInfo("Client password: ");
        try {
            userInterface.showMessage("Account number is " + myBank.createAccount(clientName, clientCPF, clientPassword));
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void showAccountBalance() {
        try {
            int accountNumber = getIntFromUser("Account number: ");
            String password = userInterface.getUserInfo("Password: ");
            userInterface.showMessage("Account balance: R$" + toCurrency(myBank.getAccountBalance(accountNumber, password)));
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void deposit() {
        try {
            int accountNumber = getIntFromUser("Account number: ");
            double value = getDoubleFromUser("Value of deposit: ");
            myBank.depositToAccount(accountNumber, value);
            userInterface.showMessage("Operation completed successfully!");
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void withdraw() {
        try {
            int accountNumber = getIntFromUser("Account number: ");
            double value = getDoubleFromUser("Value of withdraw: ");
            String password = userInterface.getUserInfo("Password: ");
            myBank.withdrawFromAccount(accountNumber, value, password);
            userInterface.showMessage("Operation completed successfully!");
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void transfer() {
        try {
            int origAccountNumber = getIntFromUser("Your account number: ");
            int destAccountNumber = getIntFromUser("Destination account number: ");
            double value = getDoubleFromUser("Value: ");
            String password = userInterface.getUserInfo("Password: ");
            myBank.transferToAccount(origAccountNumber, destAccountNumber, value, password);
            userInterface.showMessage("Transfer completed successfully!");
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void deleteAccount() {
        try {
            int accountNumber = getIntFromUser("Account number: ");
            String password = userInterface.getUserInfo("Password: ");
            myBank.deleteAccount(accountNumber, password);
            userInterface.showMessage("Account deleted successfully!");
        } catch (Exception e) {
            userInterface.showMessage(e.getMessage());
        }
    }

    private void goodbyeMessage() {
        userInterface.showMessage("Thanks for using our services, until next time!");
    }

}
