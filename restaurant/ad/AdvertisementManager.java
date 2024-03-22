package com.javarush.restaurant.ad;

import com.javarush.restaurant.ConsoleHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public void processVideos() throws NoVideoAvailableException {

        List<Advertisement> list = new ArrayList<>();
        List<Advertisement> curlist = new ArrayList<>();

        createList(0, curlist, list);

        if (list.isEmpty()) throw new NoVideoAvailableException();

        list.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getAmountPerOneDisplaying() > o2.getAmountPerOneDisplaying()) {
                    return -1;
                } else if (o1.getAmountPerOneDisplaying() < o2.getAmountPerOneDisplaying()){
                    return 1;
                } else if (o1.getAmountPerOneSecond() > o2.getAmountPerOneSecond()) {
                    return 1;
                } else if (o1.getAmountPerOneSecond() < o2.getAmountPerOneSecond()){
                    return -1;
                }
                return 0;
            }
        });

        for (Advertisement advertisement : list) {
            ConsoleHelper.writeMessage(advertisement.toString());
            advertisement.revalidate();
        }

    }

    private void createList(int index, List<Advertisement> curlist, List<Advertisement> list) {

        List<Advertisement> allAds = AdvertisementStorage.getInstance().list();
        Advertisement ad = allAds.get(index);

        if (getDuration(curlist) + ad.getDuration() > timeSeconds || ad.getHits() <= 0) {
            return;
        }

        curlist.add(ad);
        for (int i = index + 1; i < allAds.size(); i++) {
            createList(i, curlist, list);
        }
        getAppropriateList(curlist, list);
        curlist.remove(ad);

    }

    private void getAppropriateList(List<Advertisement> curlist, List<Advertisement> list) {

        long curlistAmount = getAmount(curlist);
        long curList = getAmount(list);
        if (curlistAmount > curList) {
            list.clear();
            list.addAll(curlist);
        } else if (curlistAmount == curList) {

            int curlistDuration = getDuration(curlist);
            int listDuration = getDuration(list);
            if (curlistDuration > listDuration) {
                list.clear();
                list.addAll(curlist);
            } else if (curlistDuration == listDuration) {

                if (curlist.size() < list.size()) {
                    list.clear();
                    list.addAll(curlist);
                }
            }
        }
    }

    private long getAmount(List<Advertisement> curlist) {
        long amount = 0;
        for (Advertisement advertisement : curlist) {
            amount += advertisement.getAmountPerOneDisplaying();
        }
        return amount;
    }

    private int getDuration(List<Advertisement> curlist) {
        int duration = 0;
        for (Advertisement advertisement : curlist) {
            duration += advertisement.getDuration();
        }
        return duration;
    }

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
}
