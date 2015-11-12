package test.be.kdg.schelderadarchain.generator;

import be.kdg.schelderadarchain.generator.backend.generator.BaseGenerator;
import be.kdg.schelderadarchain.generator.backend.generator.RandomLoadGenerator;

/**
 * Manual test class
 */

public class TestRandomLoadGenerator {
    public static void main(String[] args) {
        new TestRandomLoadGenerator().startTest();
    }

    private void startTest(){
        BaseGenerator generator = new RandomLoadGenerator();
        generator.start();
    }
}
