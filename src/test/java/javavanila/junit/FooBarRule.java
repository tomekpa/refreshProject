package javavanila.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FooBarRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
         System.out.println("FooBarRule");
         return base;
    }
}
