package be.kdg.schelderadarchain.processor.eta.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import be.kdg.schelderadarchain.processor.eta.strategy.calculation.*;
import be.kdg.schelderadarchain.processor.eta.strategy.trigger.*;

/**
 * Properties handler class for eta properties.
 *
 * @author Olivier Van Aken
 */
public class EtaProperties {
    private static final String ETA_METHOD;
    private static final String ETA_SHIPS;
    private static final String ETA_TRIGGER;

    private static final List<Integer> ETA_SHIP_IDS;

    protected static final String PROPERTY_FILE = "properties/eta.properties";
    protected static final Properties PROPERTIES = new Properties();

    // static block is executed before main method at the schedule of classloading
    static {
        ETA_METHOD = "eta.method";
        ETA_SHIPS = "eta.ships";
        ETA_TRIGGER = "eta.trigger";

        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException | IllegalArgumentException | NullPointerException ex) {
            // log error occurrence during reading of amqp properties
            System.exit(0);
        }

        ETA_SHIP_IDS = readEtaShips();
    }

    public static EtaCalculationMethod getEtaMethod() {
        String trigger = PROPERTIES.getProperty(ETA_METHOD);
        switch (trigger) {
            case "rough":
                return new RoughMethod();
            default:
                return new RoughMethod();
        }
    }

    public static List<Integer> getEtaShips() {
        return ETA_SHIP_IDS;
    }

    public static EtaTrigger getEtaTrigger() {
        String trigger = PROPERTIES.getProperty(ETA_TRIGGER);
        switch (trigger) {
            case "position":
                return new PositionTrigger();
            case "zone":
                return new ZoneTrigger();
            default:
                return new AlwaysTrigger();
        }
    }

    private static List<Integer> readEtaShips() {
        String[] etaShips = PROPERTIES.getProperty(ETA_SHIPS).split("\\.");
        ArrayList<Integer> shipIds = new ArrayList<>();

        for (String shipId : etaShips) {
            shipIds.add(Integer.parseInt(shipId));
        }

        return shipIds;
    }
}
