package com.javarush.cashmachine.command;

import com.javarush.cashmachine.ConsoleHelper;
import com.javarush.cashmachine.CurrencyManipulator;
import com.javarush.cashmachine.CurrencyManipulatorFactory;
import com.javarush.cashmachine.exception.InterruptOperationException;
import com.javarush.cashmachine.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {

        while (true) {

            String currencyCode = ConsoleHelper.askCurrencyCode();
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            int sum = 0;

            ConsoleHelper.writeMessage("Введите сумму:");
            String str = ConsoleHelper.readString();
            try {
                sum = Integer.parseInt(str);
                if (sum <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Введите целое положительное число!");
                continue;
            }
            if (!manipulator.isAmountAvailable(sum)){
                ConsoleHelper.writeMessage("Недостаточно средств на счету! Текущая сумма: "+ manipulator.getTotalAmount());
                continue;
            }
            Map<Integer, Integer> map;
            try {
                map = manipulator.withdrawAmount(sum);
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Нет подходящих банкнот!");
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
            }
            ConsoleHelper.writeMessage(String.format("%d %s успешно снято со счета", sum, currencyCode));
            break;
        }

    }
}
