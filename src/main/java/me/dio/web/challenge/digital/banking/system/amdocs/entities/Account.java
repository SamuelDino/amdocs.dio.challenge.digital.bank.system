package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class Account implements AccountService{
    static Integer nextNumber = 1001;
    private Integer number = nextNumber++;
    private Agency agency;
    private Bank bank;
    private Client client;
    private Double balance = 0d;
    private Map<LocalDateTime, String> history = new TreeMap<>();

    void addHistory(LocalDateTime dateTime, String operation, Double amount, Coin coin){
        final String description = operation.trim()
                + ": "
                + coin.getSymbol()
                + String.format("%.2f",amount.doubleValue())
                + " balance: "
                + coin.getSymbol()
                + String.format("%.2f",this.balance.doubleValue());
        history.put(dateTime, description);
    }
}
