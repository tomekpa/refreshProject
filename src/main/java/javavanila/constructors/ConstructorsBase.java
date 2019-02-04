package javavanila.constructors;

import javax.annotation.PostConstruct;

class ConstructorsBase {
    static {
        System.out.printf("%s - %s - %s\n", "base", "static", "block");
    }

    {
        System.out.printf("%s - %s - %s\n", "base", "instance", "block");
    }

    public ConstructorsBase() {
        System.out.printf("%s - %s\n", "base", "constructor no arg");
    }

    public ConstructorsBase(boolean isPresent) {
        System.out.printf("%s - %s\n", "base", "constructor with arg");
    }

    @PostConstruct
    public void init() {
        System.out.printf("%s - %s\n", "base", "PostConstruct");
    }

    public void hello() {
        System.out.printf("%s - %s\n", "base", "method");
    }
}
