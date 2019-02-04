package javavanila.constructors;

import javax.annotation.PostConstruct;

class ConstructorsSub extends ConstructorsBase {
    static {
        System.out.printf("%s - %s - %s\n", "sub", "static", "block");
    }
    {
        System.out.printf("%s - %s - %s\n", "sub", "instance", "block");
    }

    public ConstructorsSub() {
        super(true);
        System.out.printf("%s - %s\n", "sub", "constructor");
    }

    @PostConstruct
    public void init() {
        System.out.printf("%s - %s\n", "sub", "PostConstruct");
    }

    @Override
    public void hello() {
        // super.hello();
        System.out.printf("%s - %s\n", "sub", "method");
    }
}
