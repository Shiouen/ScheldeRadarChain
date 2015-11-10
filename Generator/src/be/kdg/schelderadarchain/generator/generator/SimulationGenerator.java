package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.dto.IncidentMessageDTO;
import be.kdg.schelderadarchain.generator.utility.JsonConverter;
import be.kdg.schelderadarchain.generator.utility.RouteFileReader;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;
import be.kdg.se3.proxy.IncidentServiceProxy;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cas on 9/11/2015.
 */
public class SimulationGenerator extends BaseGenerator implements IncidentHandler {

    private final int INCIDENT_FREQUENCY = 20;
    private final String SEPARATOR = ";";
    private final String INCIDENT_URL = "www.services4se3.com/incidentservice/simulate/";

    private List<String> shipIds;

    @Override
    public void start() {
        this.shipIds = this.getShipIds("routes");
        String randomShipId = shipIds.get(random.nextInt(shipIds.size()));
        new FrequencyTimerTask(this, randomShipId, this.INCIDENT_FREQUENCY).run();
        this.positionMessages.addAll(RouteFileReader.readPositionMessagesFromCsv(randomShipId, this.SEPARATOR));
        super.start();
    }

    public ArrayList<String> getShipIds(String folderName){
        ArrayList<String> ids = new ArrayList<>();
        try {
            File folder = new File(Thread.currentThread().getContextClassLoader().getResource(folderName).toURI());
            for (File file : folder.listFiles()) {
                if (file.isFile()){
                    String shipId = file.getName();
                    int index = shipId.indexOf(".");
                    ids.add(shipId.substring(0, index));
                }
            }
        } catch (URISyntaxException e) {
            throw new GeneratorException("Unexpected error reading the route files", e);
        }
        return ids;
    }

    @Override
    public IncidentMessage receiveIncidentMessage(String shipId){
        try {
            String incident = new IncidentServiceProxy().get(this.INCIDENT_URL + shipId);
            IncidentMessageDTO incidentMessageDTO = JsonConverter.fromObject(incident, IncidentMessageDTO.class);
            IncidentMessage incidentMessage = new IncidentMessage(incidentMessageDTO);
            return incidentMessage;

        } catch (IOException e) {
            throw new GeneratorException("Unexpected error in incident service proxy", e);
        }
    }

    @Override
    public void sendIncidentMessage(IncidentMessage incidentMessage) {
        //TODO vervangen door RabbitMQ aanroep
        System.out.println(XmlConverter.toXml(incidentMessage));
    }
}


