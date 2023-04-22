List<Circle> getCircles(int n) {
   return IntStream.rangeClosed(n) // stream<Integer>
        .mapToDouble(x -> x * 1.00) // stream<Double>
        .mapToObj(x -> new Circle(new Point(x, n + 1 - x)))
        .toList();
}