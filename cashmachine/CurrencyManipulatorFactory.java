package com.javarush.cashmachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        return map.merge(currencyCode, new CurrencyManipulator(currencyCode), (oldValue, newValue) -> oldValue);
    }


}
