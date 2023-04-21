import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

// using chatgpt
class ImList<T> {
    private final Supplier<List<T>> list;

    ImList() {
        this.list = () -> new ArrayList<T>();
    }

    private ImList(Supplier<List<T>> list) {
        this.list = list;
    }

    ImList<T> add(T t) { // use constructor Imlist(supplier<List<T>>)
        return new ImList<T>(() -> { // this supplier should return a list
            System.out.println("Adding: " + t);
            List<T> newList = new ArrayList<T>(list.get()); // why can use supplier.get() here?
            newList.add(t);
            return newList;
        });
    }

    ImList<T> set(int index, T t) {
        return new ImList<T>(() -> {
            System.out.println("Setting: " + index + ", " + t);
            List<T> newList = new ArrayList<T>(list.get());
            newList.set(index, t);
            return newList;
        });
    }

    T get(int index) {
        return this.list.get().get(index);
    }

    @Override
    public String toString() {
        return "ImList";
    }
}