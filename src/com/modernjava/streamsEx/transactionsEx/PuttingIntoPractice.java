package com.modernjava.streamsEx.transactionsEx;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class PuttingIntoPractice {

    public static void main(String[] args) {

        Trader petru = new Trader("Petru", "Suceava");
        Trader andrei = new Trader("Andrei", "Iasi");
        Trader silviu = new Trader("Silviu", "Bucuresti");

        List<Transaction> transactionList = Arrays.asList(
                new Transaction(petru, 2011, 5000),
                new Transaction(andrei, 2019, 52345),
                new Transaction(silviu, 2021, 5425),
                new Transaction(petru, 2021, 20404));

        List<Transaction> tr2021 = transactionList.stream().filter(transaction -> transaction.getYear() == 2021)
                .sorted(comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(tr2021);

        List<String> traderCities = transactionList.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(traderCities);


        String tradeStr= transactionList.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().reduce("",  (n1, n2) -> n1 + n2);
        System.out.println(tradeStr);

        boolean traders = transactionList.stream().anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("bucuresti"));
        System.out.println(traders);

        int highestValue = transactionList.stream().map(Transaction::getValue).reduce(0, Integer::max);
        System.out.println(highestValue);


    }
}
