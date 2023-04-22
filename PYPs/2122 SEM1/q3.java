import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class q3{


// a
List<Integer> convert(List<Integer> list, Function<Integer,Integer> fn) {
    return list.stream().map(fn).toList();
}

// b ??
// convert(arr,f) is like  list.filter.map
List<Integer> convert(List<Integer> list, IntPredicate f) {
    return IntStream.range(0, list.size())
        .filter(f)
        .map(x -> list.get(x)*2)
        .boxed()
        .toList();
}

// c ??
IntPredicate f = x -> x % 2 == 0;

// d 
void conway(List<Integer> list, UnaryOperator<List<Integer>> r, int n) {
    Stream.iterate(list, r)
        .limit(n) // stream<List<Integer>>
        .map(x -> // List<Integer>
            x.stream() // Stream<Integer>
            .map(y -> y == 0 ? " " : "*")
            .reduce("" , (a,b) -> a + b) // String
        ) // Stream<String>
        .forEach(x -> System.out.println(x));
}

// e
static UnaryOperator<List<Integer>> r() {
    return list -> IntStream.rangeClosed(0, list.size() -1)
        .map(x -> {
            if(list.get(x) == 1) {
                return 0;
            } else {
                if(x == 0) {
                    return list.get(x+1) == 1 ? 1 : 0; 
                } else if (x == list.size() - 1) {
                    return list.get(x-1) == 1 ? 1 : 0;
                } else {
                    return (list.get(x - 1) == 0 && list.get(x + 1) == 1)
                            || (list.get(x - 1) == 1 && list.get(x + 1) == 0)
                            ? 1 : 0;
                }
            }
        })
        .boxed()
        .toList();
} 
// f


}