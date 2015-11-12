package be.kdg.schelderadarchain.processor.eta.controller;

import java.util.List;

import be.kdg.schelderadarchain.processor.eta.model.Eta;
import be.kdg.schelderadarchain.processor.eta.strategy.calculation.EtaCalculationMethod;
import be.kdg.schelderadarchain.processor.eta.strategy.trigger.EtaTrigger;
import be.kdg.schelderadarchain.processor.model.Position;

/**
 * This controller class provides ETA information towards its users,
 * given a calculation method and a trigger for how and when to actually calculate ETA.
 *
 * @author Olivier Van Aken
 */
public class EtaController {
    private EtaCalculationMethod method;
    private EtaTrigger trigger;

    public EtaController(EtaCalculationMethod method, EtaTrigger trigger) {
        this.method = method;
        this.trigger = trigger;
    }

    /**
     * This method calculates ETA information in case its EtaTrigger allows it to.
     *
     * @param positions List of Positions used to calculate the ETA.
     * @return Eta Calculated ETA value.
     */
    public Eta eta(List<Position> positions) {
        if (trigger.isTriggeredBy(positions)) {
            return this.method.calculateEta(positions);
        }

        return null;
    }
}
