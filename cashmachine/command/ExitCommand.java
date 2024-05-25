package com.javarush.cashmachine.command;

import com.javarush.cashmachine.CashMachine;
import com.javarush.cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.cashmachine.ConsoleHelper.readString;
import static com.javarush.cashmachine.ConsoleHelper.writeMessage;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("exit.question"));
        if("y".equals(readString()))
            System.exit(0);
    }
}
