package com.javarush.restaurant.kitchen;

import com.javarush.restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {

    @Override
    public void update(Observable cook, Object order) {
        ConsoleHelper.writeMessage(order + " was cooked by " + cook);
    }

}
