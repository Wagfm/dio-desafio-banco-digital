package br.com.dio.templates;

import java.math.BigDecimal;

public interface AccountTemplate {

    void depositToAccount(double value);

    void withdrawFromAccount(double value, String password);

    void transferToAccount(AccountTemplate destAccount, double value, String password);

    BigDecimal getAccountBalance(String password);

    boolean matchesPassword(String password);

    boolean isEmpty();

}
