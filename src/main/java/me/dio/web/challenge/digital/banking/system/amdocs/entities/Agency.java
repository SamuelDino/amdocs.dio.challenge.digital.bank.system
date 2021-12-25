package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Agency {
    private String name;
    private Bank bank;
    private List<Account> accounts = new ArrayList<>();
}
