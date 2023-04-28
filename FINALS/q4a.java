// Write the entire class for q4(a) below. Do not remove this comment.
// The additional method in Command class should be written in q3a.java

class Invoker<T> {
    private final Supplier<ImList<Command>> list;

    Invoker(ImList<Command> list) {
        this.list = () -> list;
    }

    Invoker<T> addCommand(Command command) {
        return new Invoker<T>(() -> {
        ImList<Command> newList = list.get();
        newList.add(command);
        return new Invoker<T>(newList);
        });
    }

    Optional<Command<T>> executeCommand() {
        return list.get().stream()
            .forEach(x -> x.execute(city))
            .or(() -> Optional.empty());
    }

}
