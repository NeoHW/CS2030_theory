import java.util.function.Function;

abstract class Maybe<T> {

    /* 
    private Maybe(T thing) {
        this.thing = thing;
    }
    */

    abstract <R> Maybe<R> map(Function<? super T, ? extends R> mapper);

    static <T> Maybe<T> of(T item) {
        // using annoymous inner class to avoid cyclic dependency
        return new Maybe<T>() {
            private final T thing = item; // property now resides in implementation

            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                    return Maybe.<R>of(mapper.apply(thing));
            }
        
            @Override
            public String toString() {
                return "Maybe[" + thing + "]";
            }
        };
    }

    static <T> Maybe<T> empty() {
        // another annoymous inner class
        return new Maybe<T>() {
            
            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>empty(); // must meet the contract specifications
            }
        
            @Override
            public String toString() {
                return "Maybe.empty";
            }
        };
    }
}