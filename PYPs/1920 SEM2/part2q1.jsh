// File qn
String foo(String filename) { 
    return Optional.ofNullable(openFile(filename))
        .or(() -> Optional.ofNullable(openFile("default.txt"))) // Optional<myFile>
        .map(x -> x.readNum()) // Optional<Integer>
        .filter(x -> x < 10 && x >= 0)
        .map(x -> "The digit is " + x)
        .orElse("Unable to read a single digit");
}

// NRIC qn
boolean canEnter(NRIC nric) { 
    return Optional.ofNullable(nric)
        .map(x -> x.lastDigit())
        .filter(x -> MyCalendar.currDate() % 2 == x % 2)
        .orElseThrow(() -> new InvalidArgumentException()); // orElseThrow requires a supplier
}

// Internship qn
Optional<Internship> match(Resume r) {
    return optional.ofNullable(r)
        .map(r -> r.getListOfLanguages()) //Optional<ArrayList<String>>
        .filter(x -> x.contains("Java")) // ArrayList<String> and List<String> should have no diff ba
        .map(x -> findInternship(x));
}
