package spring.test.contextTwo;

import org.springframework.stereotype.Component;

@Component(value = "contextTwo")
class ContextTwoImpl implements ContextTwoInterface {

    private String contextTwoImpl;

    ContextTwoImpl() {
        this.contextTwoImpl = "ContextTwoImplDefault";
    }

    ContextTwoImpl(String init) {
        this.contextTwoImpl = init;
    }

    @Override
    public String foo() {
        return contextTwoImpl;
    }
}