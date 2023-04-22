import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }
}

class Stack<T> {
    private final List<T> list;

    Stack() {
        this.list = new ArrayList<T>();
    }

    private Stack(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    Stack<T> push(T elem) {
        Stack<T> newStack = new Stack<T>(this.list);
        newStack.list.add(0, elem);
        return newStack;
    }

    // a
    Pair<Optional<T>, Stack<T>> pop() {
        Stack<T> newStack = new Stack<T>(this.list);
        Optional<T> t = newStack.isEmpty() ? Optional.empty()
                : Optional.of(newStack.list.remove(newStack.list.size() - 1));
        return new Pair<Optional<T>, Stack<T>>(t, newStack);
    }

    boolean isEmpty() {
        return this.list.isEmpty();
    }

    public String toString() {
        return "Top -> " + this.list;
    }

}

// b

int evaluate(String expr) {
    Scanner sc = new Scanner(expr);

    sc = sc.useDelimiter(" ");
    Stack<Integer> stack = new Stack<Integer>();

    while (sc.hasNext()) {
        String term = sc.next();
        // ...
        if (term.equals("+") || term.equals("*")) {
            Pair<Optional<Integer>, Stack<Integer>> pair1 = stack.pop();
            Optional<Integer> first = pair1.first();
            stack = pair1.second();

            Pair<Optional<Integer>, Stack<Integer>> pair2 = stack.pop();
            Optional<Integer> second = pair2.first();
            stack = pair2.second();
            if (term.equals("+")) {
                stack = stack.push(first.flatMap(x -> second.map(y -> x+y)).orElseThrow());
            } else {
                stack = stack.push(first.flatMap(x -> second.map(y -> x*y)).orElseThrow());
            }
        } else {
            Integer value = Integer.parseInt(term);
            stack.push(value);
        }
    }
    Optional<Integer> optAns = stack.pop().first();
    return optAns.orElseThrow();
}

// still have to do it using completableFutures
int AsyncEvaluate(String expr) {
    Scanner sc = new Scanner(expr);

    sc = sc.useDelimiter(" ");
    Stack<CompletableFuture<Integer>> stack = new Stack<CompletableFuture<Integer>>(); // stack of Cf<Int>

    while (sc.hasNext()) {
        String term = sc.next();
        // ...
        if (term.equals("+") || term.equals("*")) {
            Pair<Optional<CompletableFuture<Integer>>, Stack<CompletableFuture<Integer>>> pair1 = stack.pop();
            Optional<CompletableFuture<Integer>> first = pair1.first();
            stack = pair1.second();

            Pair<Optional<CompletableFuture<Integer>>, Stack<CompletableFuture<Integer>>> pair2 = stack.pop();
            Optional<CompletableFuture<Integer>> second = pair2.first();
            stack = pair2.second();

            // some nonsense that i anyhow write using completablefutures
            if (term.equals("+")) {
                stack = stack.push(
                            first.flatMap(x -> // x is cf<int>
                                second.map(y -> // y is cf<int>
                                    CompletableFuture.supplyAsync(() -> x.join() + y.join())))
                        .orElseThrow());
            } else {
                stack = stack.push(
                            first.flatMap(x -> // x is cf<int>
                                second.map(y -> // y is cf<int>
                                    CompletableFuture.supplyAsync(() -> x.join() * y.join())))
                        .orElseThrow());
            }
        } else {
            int value = Integer.parseInt(term); // int
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> value); // make int into cf<int> using supplyAsync
            stack.push(future); // push cf<int> into stack<cf<int>>
        }
    }
    Optional<CompletableFuture<Integer>> optAns = stack.pop().first();
    return optAns.orElseThrow().join();
}