package vavr;

import io.vavr.control.Try;

class VavrFun {
    Try<Integer> divide(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
    }
}
