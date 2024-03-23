package com.javarush.restaurant;

import com.javarush.restaurant.ad.AdvertisementManager;
import com.javarush.restaurant.ad.NoVideoAvailableException;
import com.javarush.restaurant.kitchen.Order;
import com.javarush.restaurant.kitchen.TestOrder;
import com.javarush.restaurant.statistic.StatisticManager;
import com.javarush.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    private final int number;
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createTestOrder(){
        Order order = null;

        try {
            order = new TestOrder(this);
            processOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order "+order);
        }

        return order;
    }

    private void processOrder(Order order) {
        new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
        queue.add(order);
    }

    public Order createOrder() {

        Order order = null;

        try {
            order = new Order(this);
            processOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order "+order);
        }

        return order;

    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
