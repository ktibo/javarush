package com.javarush.cashmachine.command;

import com.javarush.cashmachine.CashMachine;
import com.javarush.cashmachine.ConsoleHelper;
import com.javarush.cashmachine.CurrencyManipulator;
import com.javarush.cashmachine.CurrencyManipulatorFactory;
import com.javarush.cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();

        String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = Integer.parseInt(digits[0]);
        int count = Integer.parseInt(digits[1]);

        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulator.addAmount(denomination, count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), count*denomination, currencyCode));

    }
}
