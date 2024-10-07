package vst.graph_visualizer.tools;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface TriConsumer<T, U, V> extends Function<T, Function<U, Consumer<V>>> {
    
    default void apply(T t, U u, V v) {
        apply(t).apply(u).accept(v);
    }
}
