class Foo {
    static int y = 1;

    Runnable bar() {
        int x = 1;
        Runnable r1 = () -> System.out.println(x);
        x = x + 1;
        return r1;
    }
    
    Runnable baz() {
        Runnable r2 = () -> System.out.println(y);
        y = y + 1;
        return r2;
    } 
}

Q : Explain why one of the lambdas compiles without error, while the other does not.

Ans : 
'r1' captures the value of the local variable x which is not effectively final (value can be changed)
'r2' captures value of static variable y which is class variable, and its value can be changed without affecting the lmabda expression

to fix this, we have to make final int x = 1, and remove the line x = x + 1;


Additional explanation:
for static var : All instances of the class share the same value. 
When a lambda expression captures the value of a static variable, it captures a reference to that shared value.
changing value of static var affects all instance of the class, but does not affect lambda expression since it ref same value

local variable stored on stack, each invocation of method creates new instance of var
lambda expression captures reference to old value, lead to unexpected behaviour, so java ensures that local vars referenced must be final or effectively final