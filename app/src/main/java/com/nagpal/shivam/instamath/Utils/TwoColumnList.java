/*
 * Copyright 2018 Shivam Nagpal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagpal.shivam.instamath.Utils;

import java.util.ArrayList;

public class TwoColumnList<E> {
    private final ArrayList<E> xArrayList = new ArrayList<>();
    private final ArrayList<E> yArrayList = new ArrayList<>();
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

    public ArrayList<E> getXArrayList() {
        return xArrayList;
    }

    public ArrayList<E> getYArrayList() {
        return yArrayList;
    }
}
