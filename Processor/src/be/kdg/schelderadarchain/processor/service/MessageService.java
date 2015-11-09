package be.kdg.schelderadarchain.processor.service;

import be.kdg.schelderadarchain.processor.model.Message;

public class MessageService {
    public MessageService() { }

    public Message add(Message message) {
        System.out.println(message);
        return message;
    }
}
