import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;

class IML<E> extends ImList<E> {
    IML() {
        super();
    }

    IML(List<? extends E> list) {
        super(list);
    }

    <R> ImList<R> map(Function<? super E, ? extends R> mapper) {
        ImList<R> newList = new ImList<R>();

        for (E t : this) {
            newList = newList.add(mapper.apply(t));
        }
        return newList;
    }

    ImList<E> filter(Predicate<? super E> pred) {
        ImList<E> newList = new ImList<E>();

        for (E t : this) {
            if (pred.test(t)) {
                newList = newList.add(t);
            }
        }
        return newList;
    }

    public void forEach(Consumer<? super E> consumer) {
        for (E t : this) {
            consumer.accept(t);
        }
    }
    <U> U reduce(U identity, 
            BiFunction<? super U, ? super E, ? extends U> acc) {
        for (E t : this) {
            identity = acc.apply(identity, t);
        }
        return identity;
    }

    <R> ImList<R> flatMap(Function<? super E, ? extends ImList<? extends R>> mapper) {
        ImList<R> newList = new ImList<R>();
        for (E t : this) {
            newList = newList.addAll(mapper.apply(t));
        }
        return newList;
    }
}
