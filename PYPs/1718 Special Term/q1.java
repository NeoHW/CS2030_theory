import java.util.stream.*;

// (a) return num of elements in a stream without using count()
int myCount(Stream<T> stream) {
    return stream.maptoInt(x -> 1)
        .reduce(0, (a,b) -> a+b);
}

//(b) count repeated subsections in string
public static long countRepeats(String str) {
    return IntStream.rangeClosed(1, str.length()-1)
        .filter( x -> 
            ((str.charAt(x) == str.charAt(x-1)) && 
            (x == str.length() -1 || str.charAt(x) != str.charAt(x+1)))
        )
        .count();
}

//(c) variance method using streams & optionals
public static OptionalDouble variance(List<Integer> data) {
    int n  = data.size();
    if (n == 0) {
        return Optional.empty();
    }

    double average = data.stream().average().orElse(0.0); // average() returns Optional<>
    return OptionalDouble.of(
        data.stream()
            .mapToDouble(x -> Math.pow(x - average, 2))
            .sum() / (n -1)
    );
}

