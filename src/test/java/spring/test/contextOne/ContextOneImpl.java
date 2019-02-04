package spring.test.contextOne;

import org.springframework.stereotype.Component;

@Component(value = "contextOneImpl")
class ContextOneImpl implements ContextOneInterface {

    private String ContextOneImpl;

    ContextOneImpl() {
        this.ContextOneImpl = "ContextOneImplDefault";
    }

    ContextOneImpl(String init) {
        this.ContextOneImpl = init;
    }

    @Override
    public String foo() {
        return ContextOneImpl;
    }
}