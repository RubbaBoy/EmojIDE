package com.uddernetworks.emojide.gui.text;

import org.apache.commons.collections4.list.GrowthList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class AutoGrowArrayListTest {

    @Test
    public void getAddingToGrow() {
        var list = new AutoGrowArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            list.setAddingToGrow(() -> finalI);
            assertEquals(i, list.getAddingToGrow().get());
        }
    }

    @Test
    public void get() {
        var list = new AutoGrowArrayList<Integer>();
        list.add(rand());

        for (int i = 1; i < 1000; i++) {
            assertNull(list.get(i));
        }
    }

    @Test
    public void set() {
        var list = new AutoGrowArrayList<Integer>();
        list.set(2, rand());
        assertNull(list.get(0));
        assertNull(list.get(1));
        assertNotNull(list.get(2));
        assertNull(list.get(3));
    }

    @Test
    public void add() {
        var list = new AutoGrowArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 999);

        assertTrue(listsEquals(list, 1, 999, 2, 3));
    }

    @Test
    public void addAll() {
        var list = new AutoGrowArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);

        list.addAll(1, Arrays.asList(10, 11, 12));

        assertTrue(listsEquals(list, 0, 10, 11, 12, 1, 2));
    }

    private <T> boolean listsEquals(List<T> list1, T... elements) {
        return listsEquals(list1, Arrays.asList(elements));
    }

    private <T> boolean listsEquals(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) return false;
        if (list1 == list2) return true;
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) return false;
        }
        return true;
    }

    private int rand() {
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    }
}
