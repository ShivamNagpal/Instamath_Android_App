package com.nagpal.shivam.instamath.Utils;

import java.util.ArrayList;

public class TwoColumnList<E> {
    private ArrayList<E> xArrayList = new ArrayList<>();
    private ArrayList<E> yArrayList = new ArrayList<>();
    private int size = 0;

    public TwoColumnList() {
    }

    public void add(E x, E y) {
        xArrayList.add(x);
        yArrayList.add(y);
        size += 1;
    }

    public E getX(int index) {
        return xArrayList.get(index);
    }

    public E getY(int index) {
        return yArrayList.get(index);
    }

    public void remove(int index) {
        xArrayList.remove(index);
        yArrayList.remove(index);
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        size = 0;
        xArrayList.clear();
        yArrayList.clear();
    }
}
