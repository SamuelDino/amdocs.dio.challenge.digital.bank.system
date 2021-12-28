package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import java.time.LocalDateTime;

public interface AccountService {
    void deposit(final Double amount);

    void withdraw(final Double amount);

    void transfer(final Account destinyAccount, final double amount);

    void internationalTransfer(final WorldBank worldBank, final Account destinyAccount, final double amount) throws Exception;

}
