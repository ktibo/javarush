package com.javarush.cashmachine;

import com.javarush.cashmachine.command.CommandExecutor;
import com.javarush.cashmachine.exception.InterruptOperationException;

public class CashMachine {

    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName()+".resourses.";

    public static void main(String[] args) {
        try {
            CommandExecutor.execute(Operation.LOGIN);
            while (true){
                Operation operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Операция прервана! Выходим..");
            System.exit(0);
        }
    }
}
