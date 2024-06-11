package raven.hsb.swsyspro_automatic_tests;

public interface TestCase {

    boolean isCorrect();
    default String getName() {
        return this.getClass().getName();
    };
}
