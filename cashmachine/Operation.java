package com.javarush.cashmachine;

import java.util.Arrays;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws UnsupportedOperationException {

        Operation value;
        try {
            value = values()[i];
        } catch (Exception e) {
            throw new UnsupportedOperationException("нет совпадений!");
        }

        if (LOGIN.equals(value))
            throw new IllegalArgumentException();

        return value;
//        return Arrays.stream(values())
//                .filter(operation -> operation.ordinal() + 1 == i)
//                .findAny()
//                .or(() -> {throw new UnsupportedOperationException("нет совпадений!");})
//                .get();
    }

}
