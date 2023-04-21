import java.util.List;

abstract class Student {
    protected final double cap;
    protected final double loan;
    protected final String id;

    Student(double cap, double loan, String id) {
        this.cap = cap;
        this.loan = loan;
        this.id = id;
    }

    double getCAP() {
        return 5.0;
    }

    abstract double getLoan();

    String getID() {
        return this.id;
    }
}

class PG extends Student {
    
    PG(double cap, double loan, String id) {
        super(cap,loan,id);
    }

    double getLoan() {
        return 111.11;
    }
}

class UG extends Student {
    
    UG(double cap, double loan, String id) {
        super(cap,loan,id);
    }

    double getLoan() {
        return 99.99;
    }
}

class Admin {

    void process(List<Student> students) {
        for (Student i : students) {
            System.out.println(i.getID() + " : " + i.getCAP());            
        }
    }
}

class Busrar {

    void process(List<Student> students) {
        for (Student i : students) {
            System.out.println(i.getID() + " : " + i.getLoan());            
        }
    }
}