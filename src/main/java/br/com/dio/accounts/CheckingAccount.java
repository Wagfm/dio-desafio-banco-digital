package br.com.dio.accounts;

import java.math.BigDecimal;

/**
 * CheckingAccount class
 *
 * @author Wagner Maciel
 */
public class CheckingAccount extends Account {
    private final int accountLimit;

    public CheckingAccount(String clientName, String clientCPF, String password, short agencyNumber, int accountNumber, int accountLimit) {
        super(clientName, clientCPF, password, agencyNumber, accountNumber);
        this.accountLimit = accountLimit;
    }

    @Override
    public void withdrawFromAccount(double value, String password) {
        BigDecimal precisionValue = BigDecimal.valueOf(value);
        BigDecimal precisionLimit = BigDecimal.valueOf(accountLimit).negate();

        if (getAccountBalance(password).subtract(precisionValue).compareTo(precisionLimit) >= 0) {
            super.withdrawFromAccount(value, password);
            return;
        }

        throw new RuntimeException("Operation exceeds the account limit!");
    }
}
