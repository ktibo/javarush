package com.javarush.cashmachine.command;

import com.javarush.cashmachine.CashMachine;
import com.javarush.cashmachine.ConsoleHelper;
import com.javarush.cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage("Введите через пробел номер карты и пин-код:");
        while (true) {
            String str = ConsoleHelper.readString();
            String[] arr = str.split(" ");
            if (arr.length != 2) {
                ConsoleHelper.writeMessage("Введите два номера через пробел!");
                continue;
            }
            if (validCreditCards.containsKey(arr[0]) && validCreditCards.getString(arr[0]).equals(arr[1]))
                return;
            ConsoleHelper.writeMessage("Не верные значения!");
        }

    }
}
