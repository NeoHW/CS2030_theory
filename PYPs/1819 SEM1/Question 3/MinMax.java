import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Optional;
import java.util.Scanner;

class MinMax {
    final int min, max;
    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public String toString() {
        return min + ", " + max;
    }

    static Optional<MinMax> findMinMax(Stream<Integer> instream) {
        Optional<Pair> o = instream
            .map(x -> new Pair(x, x))
            .reduce((x, y) -> new Pair(Math.min(x.getFirst(), y.getFirst()), 
                        Math.max(x.getSecond(), y.getSecond())));
        if (!o.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(new MinMax(o.get().getFirst(), o.get().getSecond()));
    }

}

class Pair {
    private Integer first;
    private Integer second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return this.first;
    }

    public Integer getSecond() {
        return this.second;
    }
}
