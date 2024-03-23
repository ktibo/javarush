package com.javarush.restaurant;

import com.javarush.restaurant.kitchen.Cook;
import com.javarush.restaurant.kitchen.Dish;
import com.javarush.restaurant.kitchen.Order;
import com.javarush.restaurant.kitchen.Waiter;
import com.javarush.restaurant.statistic.RandomOrderGeneratorTask;
import com.javarush.restaurant.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Restaurant {

    private static int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Cook cook1 = new Cook("Vasya");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Kolya");
        cook2.setQueue(orderQueue);


        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i + 1);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Waiter waiter = new Waiter();

        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        ExecutorService executorCooks = Executors.newCachedThreadPool();
        executorCooks.submit(cook1);
        executorCooks.submit(cook2);

        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(task);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        executor.shutdownNow();
        executorCooks.shutdownNow();
        //DirectorTablet directorTablet = new DirectorTablet();
        //directorTablet.printAdvertisementProfit();
        //directorTablet.printActiveVideoSet();
        //directorTablet.printArchivedVideoSet();

    }

}
