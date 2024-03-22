package com.javarush.restaurant;

import com.javarush.restaurant.kitchen.Cook;
import com.javarush.restaurant.kitchen.Dish;
import com.javarush.restaurant.kitchen.Order;
import com.javarush.restaurant.kitchen.Waiter;

public class Restaurant {

    public static void main(String[] args) {

        Tablet tablet = new Tablet(1);
        Cook cook = new Cook("Vasya");
        Waiter waiter = new Waiter();

        cook.addObserver(waiter);
        tablet.addObserver(cook);
        tablet.createOrder();
        //tablet.createOrder();
    }

}
