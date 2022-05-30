package br.com.dio.accounts;

import br.com.dio.clients.BankClient;
import br.com.dio.templates.AccountTemplate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Account super class
 *
 * @author Wagner Maciel
 */
@Data
public abstract class Account implements AccountTemplate {
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private BigDecimal accountBalance;
    private final short agencyNumber;
    private final int accountNumber;
    private BankClient accountHolder;
    @Getter(AccessLevel.NONE)
    private final String password;

    public Account(String clientName, String clientCPF, String password, short agencyNumber, int accountNumber) {
        accountBalance = new BigDecimal("0");
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        accountHolder = new BankClient(clientName, clientCPF);
        this.password = password;
    }

    public BigDecimal getAccountBalance(String password) {
        if (!matchesPassword(password)) throw new RuntimeException("Wrong password!");
        return accountBalance;
    }

    private BigDecimal getAccountBalance() {
        return accountBalance;
    }

    @Override
    public void depositToAccount(double value) {
        if (value < 0) throw new RuntimeException("Deposit value must be equal or greater than zero!");

        accountBalance = accountBalance.add(BigDecimal.valueOf(value));
    }

    public void withdrawFromAccount(double value, String password) {
        if (value < 0) throw new RuntimeException("Withdraw value must be equal or greater than zero!");
        if (!this.password.equals(password)) throw new RuntimeException("Wrong password!");

        accountBalance = accountBalance.subtract(BigDecimal.valueOf(value));
    }

    @Override
    public void transferToAccount(AccountTemplate destAccount, double value, String password) {
        if (value <= 0) throw new RuntimeException("Transfer value must be greater than zero!");

        BigDecimal destAccountInitBalance = ((Account) destAccount).getAccountBalance();
        BigDecimal origAccountInitBalance = this.getAccountBalance(password);

        this.withdrawFromAccount(value, password);

        BigDecimal origAccountFinalValue = this.getAccountBalance(password);
        if (origAccountInitBalance.compareTo(origAccountFinalValue) <= 0)
            throw new RuntimeException("Failure during withdrawal in transaction!");

        destAccount.depositToAccount(value);

        BigDecimal destAccountFinalBalance = ((Account) destAccount).getAccountBalance();

        if (destAccountInitBalance.compareTo(destAccountFinalBalance) >= 0) {
            this.depositToAccount(value);
            throw new RuntimeException("Failure during crediting in transaction!");
        }
    }

    @Override
    public boolean isEmpty() {
        return accountBalance.compareTo(new BigDecimal("0")) == 0;
    }

    @Override
    public boolean matchesPassword(String password) {
        return this.password.equals(password);
    }
}
