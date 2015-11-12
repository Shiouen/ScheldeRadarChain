package test.be.kdg.schelderadarchain.generator;

import be.kdg.schelderadarchain.generator.backend.generator.BaseGenerator;
import be.kdg.schelderadarchain.generator.backend.generator.SimulationGenerator;

/**
 * Manual test class
 */

public class TestSimulationGenerator {

    public static void main(String[] args) {
        new TestSimulationGenerator().startTest();
    }

    private void startTest(){
        BaseGenerator generator = new SimulationGenerator();
        generator.start();
    }
}
