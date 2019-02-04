package javavanila.streams;

import lombok.Getter;

import java.util.Optional;

public class OptionalExamples {

    @Getter
    static class Owner {
        Optional<Car> car;

        public Owner(Car car) {
            this.car = Optional.ofNullable(car);
        }
    }

    @Getter
    static class Car {
        Optional<Company> company;

        public Car(Company company) {
            this.company = Optional.ofNullable(company);
        }
    }

    @Getter
    static class Company {
        Optional<String> name;

        public Company(String name) {
            this.name = Optional.ofNullable(name);
        }

    }
}
