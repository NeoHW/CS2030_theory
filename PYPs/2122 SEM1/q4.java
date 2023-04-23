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

            // a
            void forEach(Consumer<? super T> action, int n) {
                Stream.generate(seed)
                        .limit(n)
                        .forEach(action);
            }

            // b ???
            <R> MyStream<T> map(Function<? extends T, ? extends R> mapper) {
                Stream<T> stream = Stream.generate(seed)
                    .map(mapper)
                    .
            }

        };
    }

    abstract T get();
    abstract void forEach(Consumer<? super T> action, int n);
    abstract <R> MyStream<T> map(Function<? extends T, ? extends R> mapper);

}
