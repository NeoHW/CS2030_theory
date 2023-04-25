import java.util.function.*;

class IfElse<T,U> {

    // a
    private final Predicate<T> pred;
    private final Function<T,U> left;
    private final Function<T,U> right;

    IfElse(Predicate<T> pred, Function<T,U> left, Function<T,U> right) {
        this.pred = pred;
        this.left = left;
        this.right = right;
    }

    static <T,U> IfElse<T,U> of(Predicate<T> pred, Function<T,U> left, Function<T,U> right) {
        return new IfElse<T,U>(pred, left, right);
    }

    U apply(T t) {
        return pred.test(t) ? left.apply(t) : right.apply(t) ;
    }
    
    //b
    IfElse<T,U> mapIf(IfElse<T,U> other) {
        // return new IfElse<T,U>(pred, x -> other.apply(x) , right);
        // ^ the statement above is wrong as f.mapIf(g).mapIf(h).apply(2) ≠ f.mapIf(g.mapIf(h)).apply(2) 
        return new IfElse<T,U>(pred.and(other.pred), other.left, // if both are true, use other.left (draw diagram out)
            x -> new IfElse<T,U>(pred, other.right, this.right).apply(x));
    }

    //c 
    IfElse<T,U> mapElse(IfElse<T,U> other) {
        return new IfElse<T,U>(pred.or(other.pred), // use same diagram to see
            x -> new IfElse<T,U>(pred, left, other.left).apply(x),
            other.right);
    }

    //d 
    // use andTHen to compose 2 functions
    <R> IfElse<T,R> map(Function<? super U,? extends R> mapper) {
        return new IfElse<T,R>(this.pred, left.andThen(mapper), right.andThen(mapper));
    }

    <R> IfElse<T,R> flatMap(Function<? super U,? extends IfElse<U, ? extends R>> mapper) {
        
        Function<T,R> f = x -> mapper.apply(this.apply(x)).apply(this.apply(x));
        return new IfElse<T,R>(pred, f, f);
        /*
        return new IfElse<T,R>(this.pred,
        x -> mapper.apply(left.apply(x)).apply(left.apply(x)),
        x -> mapper.apply(right.apply(x)).apply(right.apply(x)));
        */
    }
}



