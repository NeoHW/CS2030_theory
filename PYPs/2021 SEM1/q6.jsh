(a)
it returns the sum of numbers from 1 to 99

(b)
IntStream.range(1,100).reduce(0, (x,y) -> x+y)
this is a simpler one liner

(c)
parallel streams not tested (but ans is no as smth about race conditions? idk)

(d)
int count(List<String> words) {
           return words.stream() // stream<String>
               .map(word -> word.length()) // String -> int 
               .reduce(0, (x,y) -> x + y);
}

(e)
int count(List<String> words) {
    return words.stream()
        .reduce("", (x, y) -> x + y)
        .length();
}
