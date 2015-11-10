package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by Cas on 9/11/2015.
 */
public abstract class BaseGenerator implements Thread.UncaughtExceptionHandler {

    protected final Collection<PositionMessage> positionMessages = new ArrayList<>();
    protected final Collection<PositionMessageLoop> loops = new ArrayList<>();
    protected Random random = new Random();

    public void start() {
        for(PositionMessage positionMessage : positionMessages){
            PositionMessageLoop loop = new PositionMessageLoop(positionMessage);
            loops.add(loop);
            Thread thread = new Thread(loop);
            thread.setUncaughtExceptionHandler(this);
            thread.start();
        }
    }

    public void stop() {
        for(PositionMessageLoop loop : loops){
            loop.stop();
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        throw new GeneratorException("Unexpected error in position loop", e);
    }

}
