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

public abstract class Bank {
    private String name;
    private CentralBank centralBank;
    private List<Agency> agencies = new ArrayList<>();
    private Double transferRate;

    void addAgency(Agency agency){
        agencies.add(agency);
    }
}
