package be.kdg.schelderadarchain.generator.Test;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.dto.IncidentMessageDTO;
import be.kdg.schelderadarchain.generator.utility.JsonConverter;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;
import be.kdg.se3.proxy.IncidentServiceProxy;

import java.io.IOException;

/**
 * Created by Cas on 9/11/2015.
 */
public class IncidentTest {

    public static void main(String[] args) {
        getIncidentMessage("1234567");
    }

    public static void getIncidentMessage(String shipId) {

        String URL = "www.services4se3.com/incidentservice/simulate/1234567";

        try {
            String incident = new IncidentServiceProxy().get(URL);
            IncidentMessageDTO incidentMessageDTO = JsonConverter.fromObject(incident, IncidentMessageDTO.class);
            IncidentMessage incidentMessage = new IncidentMessage(incidentMessageDTO);

            String xmlString = XmlConverter.toXml(incidentMessage);

            System.out.println(xmlString);


            System.out.println(incident);
            System.out.println(incidentMessage.getshipId() + "\n" + incidentMessage.getIncidentType());

        } catch (IOException e) {
            //TODO throw custom exception
            System.out.println("fuk der loopt iet mis");
        }

    }
}
