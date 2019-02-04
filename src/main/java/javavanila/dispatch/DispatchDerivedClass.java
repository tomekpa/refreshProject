package javavanila.dispatch;

class DispatchDerivedClass extends DispatchBaseClass {

    static Dispatch staticFoo() {
        return Dispatch.DERIVED;
    }

    Dispatch foo() {
        return Dispatch.DERIVED;
    }
}
