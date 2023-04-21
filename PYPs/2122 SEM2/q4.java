import java.util.function.*;

class IfElse<T,R> {

    // a
    private final Predicate<T> pred;
    private final Function<? super T,? extends R> mapper;
    private final Function<? super T,? extends R> mapper2;

    IfElse(Predicate<T> pred, Function<? super T,? extends R> mapper, Function<? super T,? extends R> mapper2) {
        this.pred = pred;
        this.mapper = mapper;
        this.mapper2 = mapper2;
    }

    static <T,R> IfElse<T,R> of(Predicate<T> pred, Function<? super T,? extends R> mapper, Function<? super T,? extends R> mapper2) {
        return new IfElse<T,R>(pred, mapper, mapper2);
    }

    R apply(T t) {
        return pred.test(t) ? mapper.apply(t) : mapper2.apply(t) ;
    }
    
    //b
    IfElse<T,R> mapIf(IfElse<T,R> other) {
        return new IfElse<T,R>(x -> this.pred.test(x) && other.pred.test(x), mapper, mapper2);
    }

    //c 
    IfElse<T,R> mapElse(IfElse<T,R> other) {
        return new IfElse<T,R>(x -> this.pred.test(x) || other.pred.test(x), mapper, mapper2);
    }

    //d (i do not know how to do this)
    IfElse<T,R> map(Function<? super T,? extends R> mapper) {
        return new IfElse<T,R>(this.pred, x -> mapper.test(true), mapper.test(!(true)));
    }
}



