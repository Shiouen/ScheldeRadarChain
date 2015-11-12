package test.be.kdg.schelderadarchain.generator;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.generator.BaseGenerator;
import be.kdg.schelderadarchain.generator.generator.SimulationGenerator;
import be.kdg.schelderadarchain.generator.utility.RouteFileReader;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;

import java.util.Collection;

/**
 * Created by Cas on 11/11/2015.
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
