// a(i)
double pow(double a, long b) {
    return Stream.<Double>generate(() -> a)
        .limit(b)
        .reduce(1.0, (x,y) -> x * y); // not 0.0
        // NOTE! : cannot start from 0.0 in multiplication!
}


// a(ii)

double seriesPi(long n) {
    return Stream.<Integer>iterate(1, x -> x + 1)
        .limit(n) // 1,2,3,4,5,6 ...
        .map(x -> (x%2 == 0 ? 1 : -1) * (4.0/(x*2+1))) // or can use pow(-1.0, x +1) to check for + or - instead of ? :
        .reduce(0.0, (a,b) -> a + b);
}

// b
Random rand = new Random();

Circle unitCircle = new Circle(new Point(0, 0), 1.0); // circle is created here

double getRand() { // returns a value btw -1.0 and 1.0
    return rand.nextDouble() * 2.0 - 1.0;
}

double approxPi(long n) {
    return Stream.<Point>generate(() -> new Point(getRand(), getRand())) // stream<Point>
        .limit(n) // n points
        .filter(x -> unitCircle.contains(x)) 
        .count() * 4.0 / n; // formula given in qn
}
