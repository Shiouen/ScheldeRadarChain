package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cas on 10/11/2015.
 */
public class SimulationTimerTask extends TimerTask {

    private final int DELAY_FACTOR = 1000;
    private Timer timer = new Timer();
    private IncidentHandler incidentHandler;
    private String shipId;
    private int incidentFrequency;

    public SimulationTimerTask(IncidentHandler incidentHandler, String shipId, int incidentFrequency) {
        this.incidentHandler = incidentHandler;
        this.shipId = shipId;
        this.incidentFrequency = incidentFrequency;
    }

    @Override
    public void run() {
        int delay = new Random().nextInt(this.incidentFrequency) * this.DELAY_FACTOR;
        timer.schedule(new SimulationTimerTask(this.incidentHandler, shipId, this.incidentFrequency), delay);
        IncidentMessage incidentMessage = incidentHandler.receiveIncidentMessage(this.shipId);
        incidentHandler.sendIncidentMessage(incidentMessage);
    }
}
