package com.javarush.restaurant.kitchen;

import com.javarush.restaurant.ConsoleHelper;
import com.javarush.restaurant.statistic.StatisticManager;
import com.javarush.restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {

    private String name;

    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }


    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage("Start cooking - " + order);
        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime()*60, order.getDishes()));
        setChanged();
        notifyObservers(order);
    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
