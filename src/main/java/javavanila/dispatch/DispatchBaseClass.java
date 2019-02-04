package javavanila.dispatch;

public class DispatchBaseClass {

    static Dispatch staticFoo() {
        return Dispatch.BASE;
    }

    Dispatch foo() {
        return Dispatch.BASE;
    }
}
