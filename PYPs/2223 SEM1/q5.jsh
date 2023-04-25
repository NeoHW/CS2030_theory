// Answer 5a below. Do not remove this comment.

Supplier<Integer> supplier = () -> urls.stream().map(x -> processUrl(x)).reduce((x,y) -> x + y)
supplier.get() // to get the result 


// Answer 5b below. Do not remove this comment.

CompletableFuture<Integer> cf = urls.stream() // Stream<String>
    .map(x -> CompletableFuture.supplyAsync(() -> processUrl(x))) // String -> cf<Int>
    .reduce(CompletableFuture.comletedFuture(0), (x, y) -> x.thenCombine(y, (a,b) -> a + b)) // reducing to one cf<Int>

cf.join() // to get the result






