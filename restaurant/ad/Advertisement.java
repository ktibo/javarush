package com.javarush.restaurant.ad;

public class Advertisement {

    private Object content;
    private String name;
    //начальная сумма, стоимость рекламы в копейках
    private long initialAmount;
    //количество оплаченных показов
    private int hits;
    //продолжительность в секундах
    private int duration;

    long amountPerOneDisplaying;

    public void revalidate(){
        if (hits <=0) throw new UnsupportedOperationException();
        hits--;
    }


    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = this.hits <= 0 ? 0 : this.initialAmount/this.hits;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", amountPerOneDisplaying=" + amountPerOneDisplaying +
                ", amountPerOneSecond=" + getAmountPerOneSecond() +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits() {
        return hits;
    }

    public boolean isActive() {
        return hits > 0;
    }

    public long getAmountPerOneSecond() {
        return 1000*amountPerOneDisplaying/duration;
    }

}
