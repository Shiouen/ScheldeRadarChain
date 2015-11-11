package be.kdg.schelderadarchain.processor.eta.controller;

import java.sql.Timestamp;
import java.util.List;

import be.kdg.schelderadarchain.processor.eta.strategy.calculation.EtaCalculationMethod;
import be.kdg.schelderadarchain.processor.eta.strategy.trigger.EtaTrigger;
import be.kdg.schelderadarchain.processor.model.Position;

/**
 * Created by Olivier on 11-Nov-15.
 */
public class EtaController {
    private EtaCalculationMethod method;
    private EtaTrigger trigger;

    public EtaController(EtaCalculationMethod method, EtaTrigger trigger) {
        this.method = method;
        this.trigger = trigger;
    }

    public Timestamp eta(List<Position> positions) {
        if (trigger.isTriggeredBy(positions)) {
            return this.method.calculateEta(positions);
        }

        return null;
    }
}
