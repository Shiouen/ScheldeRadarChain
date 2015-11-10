package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;


/**
 * Created by Cas on 9/11/2015.
 */
public class RandomLoadGenerator extends BaseGenerator {

    private final int MIN_SHIP_ID = 1000000;
    private final int MAX_SHIP_ID = 9999999;
    private final int MAX_DISTANCE_TO_LOADING_DOCK = 80000;
    private final int LIST_SIZE = 10;
    private String[] shipIds;
    private String[] centraleIds;

    public RandomLoadGenerator(){
        super();
        this.init();
    }

    @Override
    public void start() {
        super.start();
    }

    public PositionMessage generatePositionMessage(){
        String shipId = shipIds[random.nextInt(this.LIST_SIZE)];
        String centraleId = centraleIds[random.nextInt(this.LIST_SIZE)];
        String distanceToLoadingDock = Integer.toString(random.nextInt(this.MAX_DISTANCE_TO_LOADING_DOCK));
        PositionMessage positionMessage = new PositionMessage(shipId, "", centraleId, distanceToLoadingDock);

        return null;
    }

    private void init(){
        shipIds = new String[LIST_SIZE];
        for(String shipId : shipIds){
            shipId = Integer.toString(random.nextInt(this.MAX_SHIP_ID - this.MIN_SHIP_ID) + this.MIN_SHIP_ID);
        }
        centraleIds = new String[] {"Amsterdam","Delfzijl","Eemshaven","Hengelo","Meppel","Moerdijk","Rotterdam","Terneuzen","Veghel","Vlissingen"};
    }
}
