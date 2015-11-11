package be.kdg.schelderadarchain.generator.Test;

import be.kdg.schelderadarchain.generator.generator.BaseGenerator;
import be.kdg.schelderadarchain.generator.generator.RandomLoadGenerator;

/**
 * Created by Cas on 11/11/2015.
 */
public class RandomGeneratorTest {

    public static void main(String[] args) {
        BaseGenerator generator = new RandomLoadGenerator();
        generator.start();
    }
}
