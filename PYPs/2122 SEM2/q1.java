import java.util.List;

interface Gradable {
    String getID();
    double getCAP();
}

interface Payee {
    String getID();
    double getLoan();
}


abstract class Student implements Gradable, Payee {
    private final String id;

    Student(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public double getCAP() {
        return 5.0;
    }

}

class PG extends Student {
    
    PG(String id) {
        super(id);
    }
    
    public double getLoan() {
        return 111.11;
    }
}

class UG extends Student {
    
    UG(String id) {
        super(id);
    }

    public double getLoan() {
        return 99.99;
    }
}

class Admin {
    // ? extends Gradable as List actually takes in a List of students!
    void process(List<? extends Gradable> gradables) {
        for (Gradable i : gradables) {
            System.out.println(i.getID() + " : " + i.getCAP());            
        }

        // Stream
        // gradables.stream().forEach(x -> 
        //    System.out.println(x.getID() + " : " + x.getCAP())
    }
}

class Busrar {
    // ? extends Payee as List actually takes in a List of students!
    void process(List< ? extends Student> payees) {
        for (Payee i : payees) {
            System.out.println(i.getID() + " : " + i.getLoan());            
        }
    }
}