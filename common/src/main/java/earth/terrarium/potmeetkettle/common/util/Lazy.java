package earth.terrarium.potmeetkettle.common.util;

import com.google.common.base.Suppliers;

import java.util.function.Supplier;

public class Lazy<T> {
    private final Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = Suppliers.memoize(supplier::get);
    }

    public T get() {
        return supplier.get();
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}
