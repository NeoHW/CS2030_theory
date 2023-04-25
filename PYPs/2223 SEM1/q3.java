class test{
    
// Answer 3a below. Do not remove this comment.
// this returns a stream of marks for all levels of a PA
Stream<Integer> getMarks() { 
    return levels.stream()
        .map(x -> x.getMarks());
}






// Answer 3b below. Do not remove this comment.
// This returns a stream of marks of all PAs 
stream<Optional<Integer>> getMarks() {
    return listPA.stream() // Stream<Optional<PA>>
        .map(x -> x.map( // Optional<PA> -> PA
            y -> y.getMarks() // PA -> Stream<Integer>
            .reduce(0, (a,b) -> a + b) // Integer
            ) // Optional<Integer>
        ); // Stream<Optional<Integer>>
}

}




// Answer 3c below. Do not remove this comment.
class Main {
    int getTotalMarks(List<Student> list) {
        return list.stream() // Stream<Student>
            .flatMap(x -> x.getMarks() // Student -> Stream<Optional<Integer>>
                .map(y -> y.orElse(0)) // Optional<Integer> -> Integer
            ) // Stream<Integer>
            .reduce(0, (x,y) -> x + y);
    }
}