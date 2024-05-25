package com.javarush.cashmachine.command;

import com.javarush.cashmachine.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
