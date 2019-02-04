package json;


import lombok.Getter;
import lombok.Setter;


class JacksonExamples {

    @Getter
    @Setter
    static class Animal {
        int age;
    }

    @Getter
    @Setter
    static class Dog extends Animal {
        String name;
    }

}
