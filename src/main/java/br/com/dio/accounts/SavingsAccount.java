package br.com.dio.accounts;

import java.math.BigDecimal;

/**
 * SavingsAccount class
 *
 * @author Wagner Maciel
 */
public class SavingsAccount extends Account {

    public SavingsAccount(String clientName, String clientCPF, String password, short agencyNumber, int accountNumber) {
        super(clientName, clientCPF, password, agencyNumber, accountNumber);
    }

    @Override
    public void withdrawFromAccount(double value, String password) {
        if (getAccountBalance(password).compareTo(BigDecimal.valueOf(value)) >= 0) {
            super.withdrawFromAccount(value, password);
            return;
        }

        throw new RuntimeException("Insufficient funds!");
    }
}
