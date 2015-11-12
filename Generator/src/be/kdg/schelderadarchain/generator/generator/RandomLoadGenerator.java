package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.amqp.adapter.RabbitMQSender;
import be.kdg.schelderadarchain.generator.amqp.properties.RabbitMQProperties;
import be.kdg.schelderadarchain.generator.dom.IncidentMessage;
import be.kdg.schelderadarchain.generator.dom.IncidentStatusMessage;
import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;


/**
 * Created by Cas on 9/11/2015.
 */
public class RandomLoadGenerator extends BaseGenerator {

    private final int MIN_SHIP_ID = 1000000;
    private final int MAX_SHIP_ID = 9999999;
    private final int MAX_DISTANCE_TO_LOADING_DOCK = 80000;
    private final int LIST_SIZE = 10;
    private final int NUMBER_OF_POSITION_MESSAGES = 30;
    private final String FREQUENCY = "3000";
    private final String[] shipIds = new String[LIST_SIZE];
    private final String[] centraleIds = new String[] {"Amsterdam","Delfzijl","Eemshaven","Hengelo","Meppel","Moerdijk","Rotterdam","Terneuzen","Veghel","Vlissingen"};

    public RandomLoadGenerator() {
        super();
        this.init();
    }

    @Override
    public void start() {
    }

    public PositionMessage generateRandomPositionMessage(){
        String shipId = shipIds[random.nextInt(this.LIST_SIZE)];
        String centraleId = centraleIds[random.nextInt(this.LIST_SIZE)];
        String distanceToLoadingDock = Integer.toString(random.nextInt(this.MAX_DISTANCE_TO_LOADING_DOCK));
        PositionMessage positionMessage = new PositionMessage(shipId, this.FREQUENCY, centraleId, distanceToLoadingDock);
        return positionMessage;
    }

    private void init(){
        for(String shipId : shipIds){
            shipId = Integer.toString(random.nextInt(this.MAX_SHIP_ID - this.MIN_SHIP_ID) + this.MIN_SHIP_ID);
        }
        for (int i = 0; i < this.NUMBER_OF_POSITION_MESSAGES; i++) {
           /* PositionMessage positionMessage = this.generateRandomPositionMessage();
            this.positionMessages.add(positionMessage);*/
        }
    }

    @Override
    public void sendPositionMessage(PositionMessage message) {
        String xmlMessage = XmlConverter.toXml(message);
        this.sender = new RabbitMQSender(RabbitMQProperties.getHost(), RabbitMQProperties.getSenderPositionMessageQueue(), xmlMessage);
        this.sender.open();
        this.sender.close();
    }

    @Override
    public void sendIncident(IncidentMessage incidentMessage) {

    }

    @Override
    public IncidentMessage receiveIncident(String shipId) {
        return null;
    }

    @Override
    public IncidentStatusMessage receiveIncidentStatusMessage(String shipId) {
        return null;
    }
}
