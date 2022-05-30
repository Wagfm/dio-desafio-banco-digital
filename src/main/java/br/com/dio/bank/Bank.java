package br.com.dio.bank;

import br.com.dio.agencies.Agency;

import java.math.BigDecimal;

/**
 * Bank class
 *
 * @author Wagner Maciel
 */
public class Bank {

    private static short nextAgencyNumber = 2509;
    private static Bank bankInstance;
    private final Agency bankAgency;

    private Bank() {
        bankAgency = new Agency(nextAgencyNumber);
        nextAgencyNumber++;
    }

    public static Bank getBankInstance() {
        if (bankInstance == null) bankInstance = new Bank();
        return bankInstance;
    }

    public void depositToAccount(int accountNumber, double value) {
        bankAgency.depositToAccount(accountNumber, value);
    }

    public void withdrawFromAccount(int accountNumber, double value, String password) {
        bankAgency.withdrawFromAccount(accountNumber, value, password);
    }

    public void transferToAccount(int origAccountNumber, int destAccountNumber, double value, String password) {
        bankAgency.transferToAccount(origAccountNumber, destAccountNumber, value, password);
    }

    public int createAccount(String clientName, String clientCPF, String password, int accountLimit) {
        return bankAgency.createAccount(clientName, clientCPF, password, accountLimit);
    }

    public int createAccount(String clientName, String clientCPF, String password) {
        return bankAgency.createAccount(clientName, clientCPF, password);
    }

    public BigDecimal getAccountBalance(int accountNumber, String password) {
        return bankAgency.getAccountBalance(accountNumber, password);
    }

    public void deleteAccount(int accountNumber, String password) {
        bankAgency.deleteAccount(accountNumber, password);
    }
}
