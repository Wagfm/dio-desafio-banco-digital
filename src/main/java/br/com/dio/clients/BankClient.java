package br.com.dio.clients;

import lombok.Data;

/**
 * BankClient class
 *
 * @author Wagner Maciel
 */
@Data
public class BankClient {
    private final String clientName;
    private final String clientCPF;
    private String clientDeliverAddress;
    private String clientAddress;
    private double clientIncome;

    public BankClient(String clientName, String clientCPF) {
        this.clientName = clientName;
        this.clientCPF = clientCPF;
        clientDeliverAddress = "";
        clientAddress = "";
        clientIncome = 0.00;
    }

}
