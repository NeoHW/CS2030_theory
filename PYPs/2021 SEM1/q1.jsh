class A {
    private A other;
    
    void set(A other) {
        this.other = other;
    }
           
    A get() {
        return this.other;
    }
}

// (a) jshell codes: 
A a = new A()
A b = new A()
a.set(b)
b.set(a)
a.get()
b.get()

// (b) 
interface General {
    void set(General other);
    General get();
}

class A implements General {
    private General other;
    
    public void set(General other) {
        this.other = other;
    }
           
    public General get() {
        return this.other;
    }
}

// (c)
class A implements General {
    private final General other;

    A(General other) {
        this.other = other;
    }
    
    public General set(General other) {
        return new A(other);
    }
           
    public General get() {
        return this.other;
    }
}

// (d)
class Main {
    public static void Main(String[] args) {
        A a = new A();
        a = a.set(new A(new B(new B(a))));
    }
}