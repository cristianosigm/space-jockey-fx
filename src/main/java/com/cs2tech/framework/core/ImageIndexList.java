package com.cs2tech.framework.core;

import java.util.List;

public final class ImageIndexList {

    private final List<Integer> indexes;
    private int currentIndex = 0;

    public ImageIndexList(List<Integer> indexes) {
        this.indexes = indexes;
    }

    public Integer next() {
        if (indexes.size() == 1) {
            return indexes.get(0);
        } else if (currentIndex < indexes.size() - 1) {
            currentIndex++;
            return indexes.get(currentIndex);
        } else {
            currentIndex = 0;
            return indexes.get(0);
        }
    }

    public Integer current() {
        return indexes.get(currentIndex);
    }
}
