package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class WorldBank extends Bank {
    private Coin standardCoin;
    private List<CentralBank> centralBanks = new ArrayList<>();
    private Map<Coin, Double> quotations = new HashMap<>();

    public double exchange(final Coin originCoin, final Coin destinyCoin, final Double amount) {
        double exchangedValue = -1d;
        if (!quotations.get(originCoin).isNaN() && !quotations.get(destinyCoin).isNaN()) {
            exchangedValue = amount * quotations.get(originCoin);
            exchangedValue /= quotations.get(destinyCoin);
        }
        return exchangedValue;
    }

    public void addQuotation(final Coin coin, final double quotation) {
        if (quotation > 0) {
            quotations.put(coin, quotation);
        }
    }

}
