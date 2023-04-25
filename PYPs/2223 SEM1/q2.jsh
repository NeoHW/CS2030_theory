import java.util.stream.*;

// Answer 2a below. Do not remove this comment.
<T> ImList<T> reverse (List<? extends T> list) { // use ? extends to make it as general as possible
    return list.stream()
        .map(x -> new ImList<T>().add(x)) // because reduce requires seed & element to be the same type
        .reduce(new ImList<T>() ,(x,y) -> y.addAll(x));
}


// Answer 2b below. Do not remove this comment.
<T> List<Pair<T,T>> pairing(List<? extends T> list) { 
    return IntStream.range(0, list.size() / 2)
        .mapToObj(x -> new Pair<T,T>(list.get(x * 2), list.get(x * 2 + 1))) // mapToObj as want to convert to general stream
        .toList();
}


// Answer 2c below. Do not remove this comment.
<T> List<T> merging(List<? extends Pair<? extends T, ? extends T>> list) {
    return list.stream()
        .flatMap(x -> Stream.of(x.first(), x.second())) // Stream.of() not stream.of()
        .toList();
}







