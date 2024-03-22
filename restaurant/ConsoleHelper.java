package com.javarush.restaurant;

import com.javarush.restaurant.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = bufferedReader.readLine();
        return text;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {

        List<Dish> list = new ArrayList<>();
        writeMessage("Выберите блюдо (exit для выхода): ");
        writeMessage(Dish.allDishesToString());
        String text = "";

        while (true) {
            text = readString();
            if ("exit".equals(text)) break;
            try {
                list.add(Dish.valueOf(text));
            } catch (IllegalArgumentException e) {
                writeMessage("Нет такого блюда!");
            }
        }

        return list;
    }


}
