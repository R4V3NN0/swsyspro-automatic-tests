package raven.hsb.swsyspro_automatic_tests.testcases;

import org.springframework.stereotype.Component;
import raven.hsb.swsyspro_automatic_tests.TestCase;

@Component
public class TestCase1 implements TestCase {
    @Override
    public boolean isCorrect() {
        return true;
    }

    @Override
    public String getName() {
        return "TestCase1";
    }
}
