package com.akefirad.radutils.util;

import java.util.function.Supplier;

public class Nullable<T> {
    private T value;

    public Nullable () {
    }

    public Nullable (T value) {
        this.value = value;
    }

    public T get () {
        return value;
    }

    public void set (T value) {
        this.value = value;
    }

    public T orElse (T other) {
        return value != null ? value : other;
    }

    public <X extends Throwable> T orElseThrow (Supplier<? extends X> supplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw supplier.get();
        }
    }
}
