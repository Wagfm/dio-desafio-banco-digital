package br.com.dio.agencies;

import br.com.dio.accounts.CheckingAccount;
import br.com.dio.accounts.SavingsAccount;
import br.com.dio.templates.AccountTemplate;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Agency class
 *
 * @author Wagner Maciel
 */
@Data
public class Agency {
    private static int nextAccountNumber = 1000;
    private final short agencyNumber;
    private final Map<Integer, AccountTemplate> agencyAccounts;

    public Agency(short agencyNumber) {
        agencyAccounts = new HashMap<>();
        this.agencyNumber = agencyNumber;
    }

    public int createAccount(String clientName, String clientCPF, String password, int accountLimit) {
        AccountTemplate newAccount = new CheckingAccount(clientName, clientCPF, password, agencyNumber, nextAccountNumber, accountLimit);

        agencyAccounts.put(nextAccountNumber, newAccount);

        return nextAccountNumber++;
    }

    public int createAccount(String clientName, String clientCPF, String password) {
        AccountTemplate newAccount = new SavingsAccount(clientName, clientCPF, password, agencyNumber, nextAccountNumber);

        agencyAccounts.put(nextAccountNumber, newAccount);

        return nextAccountNumber++;
    }

    public void depositToAccount(int accountNumber, double value) {
        AccountTemplate account = agencyAccounts.get(accountNumber);

        if (account == null) throw new RuntimeException("Invalid account number!");

        account.depositToAccount(value);
    }

    public void withdrawFromAccount(int accountNumber, double value, String password) {
        AccountTemplate account = agencyAccounts.get(accountNumber);

        if (account == null) throw new RuntimeException("Invalid account number!");

        account.withdrawFromAccount(value, password);
    }

    public void transferToAccount(int origAccountNumber, int destAccountNumber, double value, String password) {
        AccountTemplate origAccount = agencyAccounts.get(origAccountNumber);
        AccountTemplate destAccount = agencyAccounts.get(destAccountNumber);

        if (origAccount == null) throw new RuntimeException("Invalid origin account number!");
        if (destAccount == null) throw new RuntimeException("Invalid destination account number!");

        origAccount.transferToAccount(destAccount, value, password);
    }

    public BigDecimal getAccountBalance(int accountNumber, String password) {
        AccountTemplate account = agencyAccounts.get(accountNumber);

        if (account == null) throw new RuntimeException("Invalid account number!");

        return account.getAccountBalance(password);
    }

    public void deleteAccount(int accountNumber, String password) {
        AccountTemplate accountToRemove = agencyAccounts.get(accountNumber);
        if (accountToRemove == null) throw new RuntimeException("Invalid account number!");
        if (!accountToRemove.matchesPassword(password)) throw new RuntimeException("Wrong account password!");
        if (!accountToRemove.isEmpty()) throw new RuntimeException("Can't remove account with funds or in debt!");
        agencyAccounts.remove(accountNumber);
    }

}