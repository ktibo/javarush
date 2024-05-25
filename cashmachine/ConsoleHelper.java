package com.javarush.cashmachine;

import com.javarush.cashmachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Введите номер операции:");
        writeMessage("\t 1 - operation.INFO");
        writeMessage("\t 2 - operation.DEPOSIT");
        writeMessage("\t 3 - operation.WITHDRAW");
        writeMessage("\t 4 - operation.EXIT");
        while (true) {
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (UnsupportedOperationException | NumberFormatException e) {
                writeMessage("Неверный номер операции!");
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage("Введите через пробел номинал и количество банкнот:");
        while (true) {
            String str = readString();
            String[] arr = str.split(" ");
            if (arr.length != 2) {
                writeMessage("Введите два числа через пробел!");
                continue;
            }

            try {
                if (Integer.parseInt(arr[0]) <= 0 || Integer.parseInt(arr[1]) <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                writeMessage("Введите два целых числа!");
                continue;
            }
            return arr;
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Введите код валюты:");
        while (true) {
            String code = readString();
            if (code.length() != 3) {
                writeMessage("Код должен содержать 3 символа!");
                continue;
            }
            return code.toUpperCase();
        }
    }

    public static String readString() throws InterruptOperationException {
        String message = null;
        try {
            message = bis.readLine();
        } catch (IOException e) {
        }

        if (message.toLowerCase().equals("exit"))
            throw new InterruptOperationException();

        return message;
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

}
