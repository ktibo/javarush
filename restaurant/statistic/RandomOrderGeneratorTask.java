package com.javarush.restaurant.statistic;

import com.javarush.restaurant.Tablet;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            int index = (int)Math.random()*tablets.size();
            Tablet tablet = tablets.get(index);
            tablet.createTestOrder();

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

}