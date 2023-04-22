// merge
public static <T,U,R> Stream<R> merge(Stream<? extends T> s1, Stream<? extends U> s2, Bifunction<? super T,? super U,? extends R> lambda)
    explanation: rmb PECS
    BiFunction<? super T, ? super U, ? extends R> lambda:
    a BiFunction that takes two arguments of types T and U and returns a value of type R. 
    The ? super T and ? super U are used to indicate that the lambda function can accept super types of T and U,
    and the ? extends R is used to indicate that the lambda function can return a subtype of R.

// doubleReduce 
<R> R doubleReduce(R identity, BiFunction<T, T, R> accumulator);
??? idk

// nestedMap
public <R> Stream<R> nestedMap(Function<? super T, Function<? super T, ? extends Stream <? extends R>> lambda)
    explanation : takes in a Function that takes in any superclass of T,
    then returns another Function that can take in any supercalss of T,
    and returns Stream<? extends R> according to the return type