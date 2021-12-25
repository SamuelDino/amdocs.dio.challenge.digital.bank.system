package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Country {
    private String name;
    private Coin coin;
}
