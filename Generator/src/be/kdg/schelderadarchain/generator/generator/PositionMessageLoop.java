package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.utility.XmlConverter;

/**
 * Created by Cas on 9/11/2015.
 */
public class PositionMessageLoop implements Runnable{

    private PositionMessage positionMessage;
    private boolean stopped;

    public PositionMessageLoop(PositionMessage positionMessage) {
        this.positionMessage = positionMessage;
    }

    @Override
    public void run() {
        stopped = false;
        while(!stopped){
            try {
                Thread.sleep(Long.parseLong(positionMessage.getDelay()));
                this.sendXmlMessage(positionMessage);
            } catch (InterruptedException e) {
                throw new PositionMessageException("Unexpected position message thread interruption", e);
            }
        }
    }

    public void sendXmlMessage(PositionMessage positionMessage){
        //TODO sout vervangen door rabbitmq
        System.out.println(XmlConverter.toXml(positionMessage));
    }

    public void stop(){
        this.stopped = true;
    }
}
