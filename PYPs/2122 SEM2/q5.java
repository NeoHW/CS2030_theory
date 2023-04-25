import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

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
            List<T> newList = new ArrayList<T>(list.get()); //list.get() to get the list from the supplier
            newList.add(t);
            return newList;
        });
        /* eager evaluation given
        System.out.println("Adding: " + t);
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.add(t);
        return newList;
        */
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