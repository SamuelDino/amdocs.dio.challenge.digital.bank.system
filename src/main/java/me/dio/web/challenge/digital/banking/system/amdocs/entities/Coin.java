package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Coin {
    private String name;
    private String symbol;
}
