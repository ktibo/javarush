package com.javarush.restaurant;

import com.javarush.restaurant.ad.Advertisement;
import com.javarush.restaurant.ad.StatisticAdvertisementManager;
import com.javarush.restaurant.statistic.StatisticManager;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DirectorTablet {

    public void printAdvertisementProfit() {

        Map<Date, Integer> videoAmountPerDay = StatisticManager.getInstance().getVideoAmountPerDay();
        int total = 0;
        for (Map.Entry<Date, Integer> entry : videoAmountPerDay.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey() + ": " + entry.getValue());
            total += entry.getValue();
        }
        ConsoleHelper.writeMessage("Total: " + total);
    }

//    public void printCookWorkloading() {
//
//    }

    public void printActiveVideoSet() {

        List<Advertisement> advertisements = StatisticAdvertisementManager.getInstance().getAdvertisements(true);
        Collections.sort(advertisements, (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName()));

        for (Advertisement advertisement : advertisements) {
            ConsoleHelper.writeMessage(advertisement.toString());
        }

    }

    public void printArchivedVideoSet() {

    }

}
