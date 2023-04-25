import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class MyStream<T> {

    // try not to use streams as it then defeats the purposes of implementing myStream
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            
            public T get() {
                return seed.get();
            }
        };
    }

    abstract T get();

    // a
    void forEach(Consumer<? super T> action, int n) {
        for (int i = 0; i < n; i++) {
            action.accept(this.get());
        }
    }

    // b
    // map() should return a MyStream so that it can be chained with forEach()
    <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        return new MyStream<R>() {
            public R get() {
                // must use MyStream.this.get()
                // if not this.get() refers to own method and it will be infinite loop
                return mapper.apply(MyStream.this.get());
            }
        };
    }

}


