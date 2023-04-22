import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class MyStream<T> {
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            public T get() {
                return seed.get();
            }
        };
    }

    abstract T get();
}

// a
void forEach(Consumer<? super T> action , int n) {
    Stream<T> stream = Stream<T>.generate(seed)
        .limit(n)    
        .forEach(action);
}

// b  ???
<R> MyStream<T> map(Function<? extends T, ? extends R> mapper) {
    return this.get()
        .
}