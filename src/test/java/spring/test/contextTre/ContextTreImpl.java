package spring.test.contextTre;

//@Component(value = "contextTreImpl")
class ContextTreImpl implements ContextTreInterface {

    private String contextTreImpl;

    ContextTreImpl() {
        this.contextTreImpl = "ContextTreImplDefault";
    }

    ContextTreImpl(String init) {
        this.contextTreImpl = init;
    }

    @Override
    public String foo() {
        return contextTreImpl;
    }
}