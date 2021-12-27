package me.dio.web.challenge.digital.banking.system.amdocs.application;

import me.dio.web.challenge.digital.banking.system.amdocs.entities.*;

public class AmdocsDioDigitalSystemBank {
    public static void main(String[] args) {
        Coin dollar = new Coin("Dollar", "US$");
        Coin real = new Coin("Real", "R$");
        Coin euro = new Coin("Euro", "€");

        WorldBank worldBank = new WorldBank();
        worldBank.setName("World Bank");
        worldBank.setStandardCoin(dollar);

        worldBank.addQuotation(dollar, 1d);
        worldBank.addQuotation(real, 0.18d);
        worldBank.addQuotation(euro, 1.13d);

        Country brasil = Country.builder()
                .name("Brasil")
                .coin(real)
                .build();

        Country unitedStates = Country.builder()
                .name("United States")
                .coin(dollar)
                .build();

        Country portugal = Country.builder()
                .name("Portugal")
                .coin(euro)
                .build();

        CentralBank brasilCentralBank = CentralBank.builder()
                .name("Banco Central do Brasil")
                .country(brasil)
                .exchangeRate(0d)
                .build();

        CentralBank portugalCentralBank = CentralBank.builder()
                .name("Banco Central de Portugal")
                .country(portugal)
                .exchangeRate(0d)
                .build();

        Bank santanderPortugal = new LocalBank();
        santanderPortugal.setName("Banco Santander de Portugal");
        santanderPortugal.setCentralBank(portugalCentralBank);
        santanderPortugal.setTransferRate(10d);

        Agency lisboa = Agency.builder()
                .name("Agencia Lisboa")
                .bank(santanderPortugal)
                .build();

        santanderPortugal.getAgencies().add(lisboa);

        Bank itauBrasil = new LocalBank();
        itauBrasil.setName("Banco Itaú do Brasil");
        itauBrasil.setCentralBank(brasilCentralBank);

        Agency fortaleza = Agency.builder()
                .name("Agencia Fortaleza")
                .bank(itauBrasil)
                .build();

        itauBrasil.getAgencies().add(fortaleza);

        Client manuel = new Client();
        manuel.setName("Manoel");

        Client pedro = new Client();
        pedro.setName("Pedro");

        Client maria = new Client();
        maria.setName("Maria");

        Account manuelCurrenteAccount = new CurrenteAccount();
        manuelCurrenteAccount.setClient(manuel);
        manuelCurrenteAccount.setAgency(lisboa);
        manuelCurrenteAccount.setBank(santanderPortugal);

        manuel.getAccounts().add(manuelCurrenteAccount);

        Account pedroCurrenteAccount = new CurrenteAccount();
        pedroCurrenteAccount.setClient(pedro);
        pedroCurrenteAccount.setAgency(lisboa);
        pedroCurrenteAccount.setBank(santanderPortugal);

        pedro.getAccounts().add(pedroCurrenteAccount);


        Account mariaSavingsAccount = new SavingsAccount();
        mariaSavingsAccount.setClient(maria);
        mariaSavingsAccount.setAgency(fortaleza);
        mariaSavingsAccount.setBank(itauBrasil);

        maria.getAccounts().add(mariaSavingsAccount);

        manuelCurrenteAccount.deposit(1000d);
        manuelCurrenteAccount.withdraw(150d);
        manuelCurrenteAccount.transfer(pedroCurrenteAccount, 200);
        manuelCurrenteAccount.internationalTransfer(worldBank , mariaSavingsAccount, 500d);

        System.out.println("\nBank statement - Client: "+manuelCurrenteAccount.getClient().getName());
        manuelCurrenteAccount.getHistory().entrySet().forEach(System.out::println);

        System.out.println("\nBank statement - Client: "+pedroCurrenteAccount.getClient().getName());
        pedroCurrenteAccount.getHistory().entrySet().forEach(System.out::println);

        System.out.println("\nBank statement - Client: "+mariaSavingsAccount.getClient().getName());
        mariaSavingsAccount.getHistory().entrySet().forEach(System.out::println);
    }
}