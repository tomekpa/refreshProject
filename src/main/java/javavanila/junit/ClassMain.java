package javavanila.junit;


class ClassMain {

    private ClassOneInterface classOneInterface;
    private ClassTwoInterface classTwoInterface;

    public void setClassOneInterface(ClassOneInterface classOneInterface) {
        this.classOneInterface = classOneInterface;
    }

    public ClassMain(ClassTwoInterface classTwoInterface) {
        this.classTwoInterface = classTwoInterface;
    }

    Integer getOne() {
        return classOneInterface.foo(11, 11);
    }

    Integer getTwo() {
        return classTwoInterface.bar(22, 22);
    }
}