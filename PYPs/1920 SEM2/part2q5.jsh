import java.util.concurrent.CompletableFuture;

class CF {
       static void doSomething() { .. } // runs for an undeterminstic amt of time

       static CompletableFuture<Void> printAsync(int i) {
              return CompletableFuture.runAsync(() -> {
                     doSomething();
                     System.out.print(i);
              });
       }

       public static void main(String[] args) {
              printAsync(1).join();
              CompletableFuture.allOf(printAsync(2), printAsync(3))
                     .thenRun(() -> printAsync(4));
              doSomething();
       }
}

The call to printAsync(1).join() in main will block the main thread until it completes, which means that "1" will always be printed before any other number.
After "1" is printed, two additional CompletableFutures are created: printAsync(2) and printAsync(3). 
These may start executing at any point, and their output may interleave with each other or with the execution of doSomething() or printAsync(4).
The call to doSomething() in printAsync() may run for an undeterministic amount of time, so its output may interleave with any other output.
The CompletableFuture.allOf(printAsync(2), printAsync(3)) call returns a new CompletableFuture that completes when both printAsync(2) and printAsync(3) complete.
 The thenRun(() -> printAsync(4)) call is chained to this new CompletableFuture, which means that it will only be executed after both printAsync(2) and printAsync(3) complete.
Finally, doSomething() is called again, and its output may interleave with any other output.

Based on these considerations, we can see that the output "4" will never be printed, (.join() not called)
since the call to thenRun(() -> printAsync(4)) is chained to the CompletableFuture returned by CompletableFuture.allOf(printAsync(2), printAsync(3)),
which means that it will only be executed after both printAsync(2) and printAsync(3) complete, and we cannot guarantee when this will happen.