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
List<Object> destination = new ArrayList<>(List.of("a", "b", "c"));
List<String> source = new ArrayList<>(List.of("x", "y", "z"));
BiPredicate<? super T, ? super U> pred = (x,y) -> true; // always return true to replace all elements
replace(source, destination, pred);
System.out.println(destination); // prints "[x, y, z]"