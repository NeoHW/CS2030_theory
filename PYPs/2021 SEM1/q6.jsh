(a)
it returns the sum of numbers from 1 to 99

(b)
IntStream.range(1,100).reduce(0, (x,y) -> x+y)
this is a simpler one liner

(c)
parallel streams not tested (but ans is about race conditions i think)

(d)
int count(List<String> words) {
           return words.stream() // stream<String>
               .map(word -> word.length()) // String -> int 
               .reduce(0, (x,y) -> x + y);
}

(e)
int count(List<String> words) {
    return words.stream()
        .reduce(0, (total, word) -> total + word.length(), (a,b) -> a + b);
}
