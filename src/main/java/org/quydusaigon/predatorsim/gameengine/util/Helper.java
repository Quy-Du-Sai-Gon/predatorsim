package org.quydusaigon.predatorsim.gameengine.util;

import java.util.function.Supplier;

/**
 * A utility class to store static helper methods that the {@code gameengine}
 * package uses.
 */
public final class Helper {

    private Helper() {
    }

    /**
     * Returns a {@link Supplier} to get a lazy value using the passed in
     * {@code supplier}. The output supplier wraps the passed in {@code supplier},
     * calls {@code supplier} only once when the output supplier is first called to
     * {@linkplain Supplier#get() get} the needed value and caches it. All
     * subsequence calls to the output supplier will returns the cached value.
     * 
     * @param supplier the supplier for computing the desired lazy value.
     * @return a supplier for getting the lazy value.
     * @see Supplier
     */
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
