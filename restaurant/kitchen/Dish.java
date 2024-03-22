package com.javarush.restaurant.kitchen;

import java.util.Arrays;

public enum Dish {

    FISH(25), STEAK(30), SOUP(15), JUICE(5), WATER(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {

        String result = "";

        for (Dish value : values()) {
            if ("".equals(result)) {
                result += value;
            } else {
                result += ", " + value;
            }
        }

        return result;
    }

}
