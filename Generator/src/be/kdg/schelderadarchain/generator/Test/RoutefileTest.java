package be.kdg.schelderadarchain.generator.Test;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.utility.RouteFileReader;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Cas on 9/11/2015.
 */
public class RoutefileTest {

    public static void main(String[] args) {

        //messages printen
        Collection<PositionMessage> positionMessages = RouteFileReader.readPositionMessagesFromCsv("1234567",";");
        for(PositionMessage positionMessage : positionMessages){
            System.out.printf("id: %s\ndelay: %s\nstation: %s\ndockdistance: %s\ndate: %s\n", positionMessage.getShipId(), positionMessage.getDelay(), positionMessage.getStationId(), positionMessage.getDistanceToLoadingDock(), positionMessage.getTimestamp().toString());
            System.out.println("---");
        }

        //xml messages printen
        for(PositionMessage positionMessage : positionMessages){
            System.out.println(XmlConverter.toXml(positionMessage));
        }
    }
}
