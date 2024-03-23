package com.javarush.restaurant.ad;

import com.javarush.restaurant.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public List<Advertisement> getAdvertisements(boolean active){
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (active && advertisement.getHits() > 0 || !active && advertisement.getHits() <=0)
                list.add(advertisement);
        }
        return list;
    }


    private static final StatisticAdvertisementManager INSTANCE = new StatisticAdvertisementManager();
    private StatisticAdvertisementManager() {
    }
    public static StatisticAdvertisementManager getInstance(){
        return INSTANCE;
    }
}
