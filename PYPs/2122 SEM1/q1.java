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
        return this.update(elem, Optional.empty());
    }
    
    ImList<T> set(int index, T elem) {
        return this.update(elem, Optional.of(index));
    }

    // this should be wrong as it dosent take into account remove method
    ImList<T> update(T elem, Optional<Integer> index) {
        ImList<T> newList = new ImList<T>(this.list);
        if (index.isPresent()) {
            newList.list.set(index.get(), elem)
        } else {
            newList.list.add(elem);   
        }
        return newList;
    }
}