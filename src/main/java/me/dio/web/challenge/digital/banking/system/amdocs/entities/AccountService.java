package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import java.time.LocalDateTime;

public interface AccountService {
    default void deposit(final Account account, final Double amount) {
        if (amount.doubleValue() > 0) {
            account.setBalance(account.getBalance() + amount.doubleValue());
            account.addHistory(LocalDateTime.now().plusNanos(1), "deposit", amount.doubleValue(), account.getBank().getCentralBank().getCountry().getCoin());
        }
    }

    default void withdraw(final Account account, final Double amount) {
        if (account.getBalance().doubleValue() >= amount.doubleValue()) {
            account.setBalance(account.getBalance() - amount.doubleValue());
            account.addHistory(LocalDateTime.now().plusNanos(2), "withdraw", amount.doubleValue(), account.getBank().getCentralBank().getCountry().getCoin());
        }
    }

    default void transfer(final Account originAccount, final Account destinyAccount, final double amount) {
        if (amount > 0) {
            if (originAccount.getBank().getCentralBank() == destinyAccount.getBank().getCentralBank()) {
                if (originAccount.getBalance().doubleValue() > (amount + originAccount.getBank().getTransferRate())) {
                    originAccount.setBalance(originAccount.getBalance() - amount);
                    originAccount.addHistory(LocalDateTime.now().plusNanos(3), "transfer to: " + destinyAccount.getClient(), amount, originAccount.getBank().getCentralBank().getCountry().getCoin());
                    originAccount.setBalance(originAccount.getBalance() - originAccount.getBank().getTransferRate());
                    originAccount.addHistory(LocalDateTime.now().plusNanos(4), "transfer rate", originAccount.getBank().getTransferRate(), originAccount.getBank().getCentralBank().getCountry().getCoin());
                    destinyAccount.setBalance(destinyAccount.getBalance() + amount);
                    destinyAccount.addHistory(LocalDateTime.now().plusNanos(5), "transfered by: " + originAccount.getClient(), amount, destinyAccount.getBank().getCentralBank().getCountry().getCoin());
                }
            }
        }
    }

    default void internationalTransfer(final WorldBank worldBank, final Account originAccount, final Account destinyAccount, final double amount) {
        if (amount - originAccount.getBank().getCentralBank().getExchangeRate() > 0) {
            if (originAccount.getBank().getCentralBank() != destinyAccount.getBank().getCentralBank()) {
                final double exchangedAmount = worldBank
                        .exchange(originAccount.getBank().getCentralBank().getCountry().getCoin()
                                , destinyAccount.getBank().getCentralBank().getCountry().getCoin()
                                , amount - originAccount.getBank().getCentralBank().getExchangeRate());

                if (exchangedAmount > 0){
                    originAccount.setBalance(originAccount.getBalance() - amount);
                    originAccount.addHistory(LocalDateTime.now().plusNanos(6), "international transfer to: "+ destinyAccount.getClient(), amount, originAccount.getBank().getCentralBank().getCountry().getCoin());
                    originAccount.setBalance(originAccount.getBalance() - originAccount.getBank().getCentralBank().getExchangeRate());
                    originAccount.addHistory(LocalDateTime.now().plusNanos(7), "transfer rate", originAccount.getBank().getCentralBank().getExchangeRate(), originAccount.getBank().getCentralBank().getCountry().getCoin());
                    destinyAccount.setBalance(destinyAccount.getBalance() + exchangedAmount);
                    destinyAccount.addHistory(LocalDateTime.now().plusNanos(8), "international transfer by: " + originAccount.getClient(), exchangedAmount, destinyAccount.getBank().getCentralBank().getCountry().getCoin());
                }
            }
        }
    }

}
