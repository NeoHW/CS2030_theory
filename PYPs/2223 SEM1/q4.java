import java.util.ArrayList;

class test {
// Answer 4a below. Do not remove this comment.

/* ans that i did myself
Optional<E> reduce(BinaryOperator<E> accumulator) {
    return this.isEmpty() 
        ? Optional.<E>empty() // no elements
        : (this.size() == 1 
            ? Optional.<E>of(this.get(0)) // 1 element
            : this.elems.stream() // Stream<E>
                .reduce(accumulator)) // Optional<E>
    ;
}
 */

// NOTE: this is about currying
Optional<E> reduce(Function<? super E, Function<? super E, ? extends E>> acc) {
    return this.size() == 0 ? Optional.<E>empty() : 
        Optional.of(elems.get(0))
            .map(x -> // E
                    this.remove(0) // Imlist<E> without first element
                    .reduce(x, (a,b) -> acc.apply(a).apply(b)) // E
                ); // Optional<E>
}

// to test out how it works
// new ImList<Integer>(List.of(1,2,3)).reduce((x,y) -> x + y);

}


// Answer 4b below. Do not remove this comment.

//4b is about annoymous inner classes : prof ans
static <E> ImList<E> of() {
    return new ImList<E>();
}

static <E> ImList<E> of(List<E> list) {
    return new ImList<E>(list);
}


interface ImList<E> extends Iterable<E> {
    // interfaces do not have constructors or properties
    /* 
    private final ArrayList<E> elems;

    private ImList() {
        this.elems = new ArrayList<E>();
    }

    private ImList(List<? extends E> list) {
        this.elems = new ArrayList<E>(list);
    }
    */

    static <E> ImList<E> of() {
        return ImList.of(List.of());
    }

    static <E> ImList<E> of(List<E> list) {
        // creating the annoymous inner class implementation of the interface as we do not have constructors
        return new ImList<E>() {
            private final ArrayList<E> elems = new ArrayList<E>(list); // property reside in implementation

            public Optional<E> reduce(Function<? super E, Function<? super E, ? extends E>> acc) {
                return this.size() == 0 ? Optional.<E>empty() : 
                    Optional.of(elems.get(0))
                        .map(x -> // E
                                this.remove(0) // Imlist<E> without first element
                                .reduce(x, (a,b) -> acc.apply(a).apply(b)) // E
                            ); // Optional<E>
            }
        };
    }

    // all the contracts of ImList Interface
    public Optional<E> reduce(Function<? super E, Function<? super E, ? extends E>> acc);

}

















