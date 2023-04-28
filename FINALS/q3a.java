// Write the entire class for q3(a) below. Do not remove this comment.
// You will need to add another method in q4(a) to this class later.

abstract class Command<T> {

    abstract T execute(T t);

    abstract Command<T> collate(Command<T> other);

}