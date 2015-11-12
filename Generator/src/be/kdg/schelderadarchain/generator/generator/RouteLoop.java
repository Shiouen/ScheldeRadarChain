package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.dom.Route;

import java.util.Random;

/**
 * Created by Cas on 9/11/2015.
 */
public class RouteLoop implements Runnable{


    private Route route;
    private boolean stopped;
    private MessageHandler handler;
    private Random random = new Random();
    private int incidentFrequency;
    private final int MAX_INCIDENT_FREQUENCY = 100;
    private boolean stationary;
    private PositionMessage currentPositionMessage;

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
                    throw new PositionMessageException("Unexpected position message thread interruption", e);
                }

                while(this.stationary){
                    try {
                        Thread.sleep(Long.parseLong(positionMessage.getDelay()));
                        this.handler.sendPositionMessage(positionMessage);
                        IncidentStatusMessage statusMessage = this.handler.receiveIncidentStatusMessage(positionMessage.getShipId());
                        if (!statusMessage.getStatus()) this.stationary = false;
                    } catch (InterruptedException e) {
                        throw new PositionMessageException("Unexpected position message thread interruption", e);
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
        //TODO remove
        System.out.println("alles blijft staan");
    }

    public void setStationary(boolean stationary, String zone) {
        if(this.currentPositionMessage.getStationId().equalsIgnoreCase(zone)) this.stationary = stationary;
        //TODO remove
        System.out.println("alles blijft staan in zone " + zone);
    }
}
