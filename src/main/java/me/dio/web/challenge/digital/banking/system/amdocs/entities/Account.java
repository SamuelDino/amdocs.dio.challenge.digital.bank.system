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

    @Override
    public void deposit(final Double amount) {
        if (amount.doubleValue() > 0) {
            this.setBalance(this.getBalance() + amount.doubleValue());
            this.addHistory(LocalDateTime.now().plusNanos(1), "deposit", amount.doubleValue(), this.getBank().getCentralBank().getCountry().getCoin());
        }
    }
    @Override
    public void withdraw(final Double amount) {
        if (this.getBalance().doubleValue() >= amount.doubleValue()) {
            this.setBalance(this.getBalance() - amount.doubleValue());
            this.addHistory(LocalDateTime.now().plusNanos(2), "withdraw", amount.doubleValue(), this.getBank().getCentralBank().getCountry().getCoin());
        }
    }
    @Override
    public void transfer(final Account destinyAccount, final double amount) {
        if (amount > 0) {
            if (this.getBank().getCentralBank() == destinyAccount.getBank().getCentralBank()) {
                if (this.getBalance().doubleValue() > (amount + this.getBank().getTransferRate())) {
                    this.setBalance(this.getBalance() - amount);
                    this.addHistory(LocalDateTime.now().plusNanos(3), "transfer to: " + destinyAccount.getClient(), amount, this.getBank().getCentralBank().getCountry().getCoin());
                    this.setBalance(this.getBalance() - this.getBank().getTransferRate());
                    this.addHistory(LocalDateTime.now().plusNanos(4), "transfer rate", this.getBank().getTransferRate(), this.getBank().getCentralBank().getCountry().getCoin());
                    destinyAccount.setBalance(destinyAccount.getBalance() + amount);
                    destinyAccount.addHistory(LocalDateTime.now().plusNanos(5), "transfered by: " + this.getClient(), amount, destinyAccount.getBank().getCentralBank().getCountry().getCoin());
                }
            }
        }
    }
    @Override
    public void internationalTransfer(final WorldBank worldBank, final Account destinyAccount, final double amount) throws Exception {
        if (amount - this.getBank().getCentralBank().getExchangeRate() > 0) {
            if (this.getBank().getCentralBank() != destinyAccount.getBank().getCentralBank()) {
                final double exchangedAmount = worldBank
                        .exchange(this.getBank().getCentralBank().getCountry().getCoin()
                                , destinyAccount.getBank().getCentralBank().getCountry().getCoin()
                                , amount - this.getBank().getCentralBank().getExchangeRate());

                if (exchangedAmount > 0){
                    this.setBalance(this.getBalance() - amount);
                    this.addHistory(LocalDateTime.now().plusNanos(6), "international transfer to: "+ destinyAccount.getClient(), amount, this.getBank().getCentralBank().getCountry().getCoin());
                    this.setBalance(this.getBalance() - this.getBank().getCentralBank().getExchangeRate());
                    this.addHistory(LocalDateTime.now().plusNanos(7), "transfer rate", this.getBank().getCentralBank().getExchangeRate(), this.getBank().getCentralBank().getCountry().getCoin());
                    destinyAccount.setBalance(destinyAccount.getBalance() + exchangedAmount);
                    destinyAccount.addHistory(LocalDateTime.now().plusNanos(8), "international transfer by: " + this.getClient(), exchangedAmount, destinyAccount.getBank().getCentralBank().getCountry().getCoin());
                }
            }
        }
    }

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
