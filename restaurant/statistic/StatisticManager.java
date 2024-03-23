package com.javarush.restaurant.statistic;

import com.javarush.restaurant.kitchen.Cook;
import com.javarush.restaurant.statistic.event.EventDataRow;
import com.javarush.restaurant.statistic.event.EventType;

import java.util.*;

public class StatisticManager {

    private StatisticStorage statisticStorage = new StatisticStorage();

    public Map<Date, Integer> getVideoAmountPerDay (){
        Map<Date, Integer> map = new TreeMap<>();
        for (EventDataRow eventDataRow : statisticStorage.get(EventType.SELECTED_VIDEOS)) {
            Date date = atStartOfDay(eventDataRow.getDate());
            int time = eventDataRow.getTime();
            map.compute(date, (date1, integer) -> integer == null ? time : integer + time);
        }
        return map;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage{
        private HashMap<EventType, List<EventDataRow>> storage = new HashMap<EventType, List<EventDataRow>>();

        public HashMap<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        public List<EventDataRow> get(EventType eventType) {
            return storage.get(eventType);
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        public StatisticStorage() {
            for (EventType value : EventType.values()) {
                storage.put(value, new ArrayList<EventDataRow>());
            }
        }
    }

    private Date atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static final StatisticManager INSTANCE = new StatisticManager();
    private StatisticManager() {
    }
    public static StatisticManager getInstance(){
        return INSTANCE;
    }

}
