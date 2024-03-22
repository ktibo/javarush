package com.javarush.restaurant;

import com.javarush.restaurant.ad.AdvertisementManager;
import com.javarush.restaurant.ad.NoVideoAvailableException;
import com.javarush.restaurant.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    private final int number;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {

        Order order = null;

        try {
            order = new Order(this);
            new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();
            if (!order.isEmpty()) {
                setChanged();
                notifyObservers(order);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order "+order);
        }

        return order;

    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
