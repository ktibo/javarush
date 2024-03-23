package com.javarush.restaurant.kitchen;

import com.javarush.restaurant.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();

        ConsoleHelper.writeMessage(toString());
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime() {

        int duration = 0;
        for (Dish dish : dishes) {
            duration+=dish.getDuration();
        }

        return duration;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        return "Your order: " + dishes + " of " + tablet + ", cooking time " + getTotalCookingTime() + "min ("+getTotalCookingTime()*60+"sec)";
    }
}
