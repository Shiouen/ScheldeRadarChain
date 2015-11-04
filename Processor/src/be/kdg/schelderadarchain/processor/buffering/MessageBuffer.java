package be.kdg.schelderadarchain.processor.buffering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.kdg.schelderadarchain.processor.model.Message;

/**
 * Created by Olivier on 04-Nov-15.
 */
public class MessageBuffer {
    private HashMap<Integer, List<Message>> messages;

    public MessageBuffer() {
        this.messages = new HashMap<>();
    }

    public void buffer(Message message) {
        int shipId = message.getShipId();

        if (this.messages.get(shipId) == null) {
            this.messages.put(shipId, new ArrayList<>());
        }

        this.messages.get(shipId).add(message);
    }
}
