package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.amqp.adapter.AMQPCommunicator;
import be.kdg.schelderadarchain.generator.backend.dom.Route;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Base class for the main engine, responsible for starting route loops
 * and the handling of to send/receive messages
 *
 * @autor Cas Decelle
 */

public abstract class BaseGenerator implements Thread.UncaughtExceptionHandler, MessageHandler {

    protected final Collection<Route> routes = new ArrayList<>();
    protected final Collection<RouteLoop> loops = new ArrayList<>();
    protected final Logger logger = Logger.getLogger(this.getClass());
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
