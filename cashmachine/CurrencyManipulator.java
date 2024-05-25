package com.javarush.cashmachine;

import com.javarush.cashmachine.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        // attempts by max denomination
        for (Integer denomination : denominations.keySet()) {

            TreeMap<Integer, Integer> copyMap = new TreeMap<>((SortedMap<Integer, Integer>) denominations);
            TreeMap<Integer, Integer> newMap = new TreeMap<>(Comparator.reverseOrder());
            Iterator<Map.Entry<Integer, Integer>> iterator = copyMap.entrySet().iterator();
            int sum = 0;
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                int current = entry.getKey();

                if (current>denomination)
                    continue; // next step

                int count = entry.getValue();
                while (count > 0) {
                    if (sum + current > expectedAmount) {
                        break;
                    }
                    sum += current;
                    newMap.merge(current, 1, Integer::sum);
                    entry.setValue(--count);
                    if (sum == expectedAmount) {
                        denominations = copyMap;
                        return newMap;
                    }
                }
            }
        }
        throw new NotEnoughMoneyException();
    }


    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public boolean hasMoney() {
        return getTotalAmount() == 0;
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            total += entry.getKey() * entry.getValue();
        }
        return total;
    }

    public void addAmount(int denomination, int count) {
        denominations.merge(denomination, count, Integer::sum);
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>(Comparator.reverseOrder());
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
