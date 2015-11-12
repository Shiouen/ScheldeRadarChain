package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.generator.dom.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by Cas on 9/11/2015.
 */
public abstract class BaseGenerator implements Thread.UncaughtExceptionHandler, MessageHandler {

    protected final Collection<Route> routes = new ArrayList<>();
    protected final Collection<RouteLoop> loops = new ArrayList<>();
    protected Random random = new Random();
    protected AMQPCommunicator sender;

    public abstract void start();

    public final void stop() {
        for(RouteLoop loop : loops){
            loop.stop();
        }
    }

    @Override
    public final void uncaughtException(Thread t, Throwable e) {
        throw new GeneratorException("Unexpected error in route loop", e);
    }

}
