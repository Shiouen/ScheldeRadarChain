package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.backend.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.backend.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.backend.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.backend.dom.Route;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * This class sends position and incident messages from a given route file
 *
 * @author Cas Decelle
 */

public class RouteLoop implements Runnable{


    private Route route;
    private boolean stopped;
    private MessageHandler handler;
    private Random random = new Random();
    private int incidentFrequency;
    private final int MAX_INCIDENT_FREQUENCY = 100;
    private final int ILLEGAL_MOVE_FREQUENCY = 30;
    private final int MAX_ILLEGAL_MOVE_FREQUENCY = 100;
    private final String NO_DELAY = "0";
    private boolean stationary;
    private PositionMessage currentPositionMessage;
    private final Logger logger = Logger.getLogger(this.getClass());

    public RouteLoop(Route route, MessageHandler handler, int incidentFrequency) {
        this.route = route;
        this.handler = handler;
        this.incidentFrequency = incidentFrequency;
    }

    @Override
    public void run() {
        stationary = false;
        stopped = false;
        while(!stopped){
            for(PositionMessage positionMessage : this.route.getPositionMessages()){
                this.currentPositionMessage = positionMessage;

                try {
                    Thread.sleep(Long.parseLong(positionMessage.getDelay()));
                    this.handler.sendPositionMessage(positionMessage);
                    if ( random.nextInt(this.MAX_INCIDENT_FREQUENCY) <= this.incidentFrequency ) {
                        IncidentMessage incidentMessage = this.handler.receiveIncident(positionMessage.getShipId());
                        this.handler.sendIncident(incidentMessage);
                    }

                } catch (InterruptedException e) {
                    String message = "Unexpected position message thread interruption";
                    logger.error(message);
                    throw new RouteException(message, e);
                }

                while(this.stationary){
                    try {
                        Thread.sleep(Long.parseLong(positionMessage.getDelay()));
                        this.handler.sendPositionMessage(positionMessage);
                        IncidentStatusMessage statusMessage = this.handler.receiveIncidentStatus(positionMessage.getShipId());
                        if (random.nextInt(this.MAX_ILLEGAL_MOVE_FREQUENCY) <= this.ILLEGAL_MOVE_FREQUENCY) this.handler.sendPositionMessage(new PositionMessage(this.NO_DELAY));
                        if (!statusMessage.getStatus()) this.stationary = false;
                    } catch (InterruptedException e) {
                        String message = "Unexpected position message thread interruption";
                        logger.error(message);
                        throw new RouteException(message, e);
                    }
                }
            }
        }
    }

    public void stop(){
        this.stopped = true;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public void setStationary(boolean stationary, String zone) {
        if(this.currentPositionMessage.getStationId().equalsIgnoreCase(zone)) this.stationary = stationary;
    }
}
