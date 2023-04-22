// (a)
static void <T> replace(List<T> src, List<T> dst, BiPredicate<? super T, ? super U> pred) {
    if (src.size() == dst.size()) {
        for (int i = 0; i < src.size(); i++) {
            if (pred.apply(src.get(i), dst.get(i))) { // NOTE: bifunction uses .apply
                dst.set(i, src.get(i));
            } 
        }
    }
}

// (b)
// all elements of the destination are always replaced by the source.
BiPredicate<? super T, ? super U> pred = (x,y) -> true;