package com.javarush.restaurant.statistic.event;

import com.javarush.restaurant.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {
    private long amount;
    private List<Advertisement> optimalVideoSet;
    private int totalDuration;
    private Date currentDate;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.amount = amount;
        this.optimalVideoSet = optimalVideoSet;
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
