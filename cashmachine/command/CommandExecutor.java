package com.javarush.cashmachine.command;

import com.javarush.cashmachine.Operation;
import com.javarush.cashmachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandExecutor {

    private static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    static {
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    public static void execute(Operation operation) throws InterruptOperationException {
        allKnownCommandsMap.get(operation).execute();
    }

}
