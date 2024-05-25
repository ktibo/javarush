package com.javarush.cashmachine.command;

import com.javarush.cashmachine.CashMachine;
import com.javarush.cashmachine.ConsoleHelper;
import com.javarush.cashmachine.CurrencyManipulator;
import com.javarush.cashmachine.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        StringBuilder builder = new StringBuilder();
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            builder.append("\t" + manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
        }
        ConsoleHelper.writeMessage(builder.isEmpty() ? res.getString("no.money") : builder.toString());

    }
}
