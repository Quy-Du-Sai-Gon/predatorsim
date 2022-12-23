package org.quydusaigon.predatorsim.gameengine.util;

import java.util.function.Supplier;

public final class Helper {

    private Helper() {
    }

    public static <T> Supplier<T> lazyValue(Supplier<T> supplier) {
        return new Supplier<>() {
            T value;

            @Override
            public T get() {
                if (value == null) {
                    value = supplier.get();
                }
                return value;
            }
        };
    }

}
