import java.util.*;

class ImList<T> {
    private final List<T> list;
    
    ImList() {
        this.list = new ArrayList<T>();
    }

    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }
    /*
    ImList<T> add(T elem) {
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.add(elem);
        return newList;
    }

    ImList<T> set(int index, T elem) {
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.set(index, elem);
        return newList;
    }
     */

    ImList<T> add(T elem) {
        return update(list.size(), elem);
    }

    ImList<T> set(int index, T elem) {
        return update(index, elem);
    }

    ImList<T> update(int index, T elem) {
        List<T> newList = new ArrayList<>(list);
        if (index >= newList.size()) {
            newList.add(elem);
        } else {
            newList.set(index, elem);
        }
        return new ImList<>(newList);
    }
}