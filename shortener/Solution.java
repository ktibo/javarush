package com.javarush.shortener;

import com.javarush.shortener.strategy.*;
import com.javarush.shortener.tests.FunctionalTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {

        new FunctionalTest().testHashMapStorageStrategy();
        new FunctionalTest().testOurHashMapStorageStrategy();
        new FunctionalTest().testHashBiMapStorageStrategy();
        new FunctionalTest().testOurHashBiMapStorageStrategy();

//        testStrategy(new HashMapStorageStrategy(), 20000);
//        testStrategy(new OurHashBiMapStorageStrategy(), 20000);
//        testStrategy(new HashBiMapStorageStrategy(), 20000);
//        testStrategy(new DualHashBidiMapStorageStrategy(), 20000);
        //testStrategy(new OurHashMapStorageStrategy(), 200);
        //testStrategy(new FileStorageStrategy(), 200);

    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){

        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        long time1 = System.currentTimeMillis();
        Set<Long> ids = getIds(shortener, strings);
        long time2 = System.currentTimeMillis();
        Helper.printMessage(String.valueOf(time2 - time1));

        time1 = System.currentTimeMillis();
        Set<String> strings1 = getStrings(shortener, ids);
        time2 = System.currentTimeMillis();
        Helper.printMessage(String.valueOf(time2 - time1));

        if (strings.equals(strings1)){
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }



    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }


}
