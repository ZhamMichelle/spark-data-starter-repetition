package com.example.starter.unsafe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author zhamilya on 4/16/24
 */
public class OrderedBag<T> {
    private List<T> list;

    public OrderedBag(T[] args) {
        this.list = new ArrayList<>(asList(args));
    }

    public T takeAndRemove() {
        return list.remove(0);
    }

    public int size() {
        return list.size();
    }
}
