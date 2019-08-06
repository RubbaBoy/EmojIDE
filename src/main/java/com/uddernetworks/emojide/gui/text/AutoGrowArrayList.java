package com.uddernetworks.emojide.gui.text;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class AutoGrowArrayList<E> extends ArrayList<E> {
    private Supplier<E> addingToGrow;

    public AutoGrowArrayList() {
        super();
    }

    public AutoGrowArrayList(Supplier<E> addingToGrow) {
        super();
        this.addingToGrow = addingToGrow;
    }

    public AutoGrowArrayList(@NotNull Collection<? extends E> collection) {
        super(collection);
    }

    /**
     * The element to add when the list must grow.
     *
     * @param addingToGrow The element
     * @return The current {@link AutoGrowArrayList} object for chain invocation
     */
    public AutoGrowArrayList<E> setAddingToGrow(Supplier<E> addingToGrow) {
        this.addingToGrow = addingToGrow;
        return this;
    }

    /**
     * Gets the element that will be added when the list must grow, this is by default `null`.
     *
     * @return The element
     */
    public Supplier<E> getAddingToGrow() {
        return addingToGrow;
    }

    private void growToSafe(int index) {
        IntStream.range(0, Math.max(index - size() + 1, 0)).forEach(i -> add(this.addingToGrow == null ? null : this.addingToGrow.get()));
    }

    @Override
    public E get(int index) {
        growToSafe(index);
        return super.get(index);
    }

    @Override
    public E set(int index, E element) {
        growToSafe(index);
        return super.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        growToSafe(index);
        super.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        growToSafe(index);
        return super.addAll(index, c);
    }

    @NotNull
    @Override
    public AutoGrowArrayList<E> subList(int fromIndex, int toIndex) {
        return new AutoGrowArrayList<>(super.subList(fromIndex, toIndex)).setAddingToGrow(this.addingToGrow);
    }
}
