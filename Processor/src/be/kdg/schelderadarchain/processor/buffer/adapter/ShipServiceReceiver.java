package be.kdg.schelderadarchain.processor.buffer.adapter;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.kdg.se3.proxy.ShipServiceProxy;

import be.kdg.schelderadarchain.processor.buffer.dto.ShipServiceShip;
import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;
import be.kdg.schelderadarchain.processor.model.Ship;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;

public final class ShipServiceReceiver {
    private boolean success;
    private int attempts;

    private final ShipServiceProxy shipService;

    public ShipServiceReceiver() {
        this.success = false;
        this.attempts = BufferProperties.getShipServiceReceiverAttempts();

        this.shipService = new ShipServiceProxy();
    }

    public boolean getSuccess() { return this.success; }

    public final Ship receive(Integer shipId) {
        String imo = shipId.toString();
        String baseUrl = "www.services4se3.com/shipservice";
        String url = String.format("%s/%s", baseUrl, imo);

        Ship ship = null;

        // while no successful connection and positive number of attempts left
        while (!this.success && this.attempts > 0) {
            ship = receive(url);
        }

        return ship;
    }

    private final Ship receive(String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        Ship ship = null;

        ShipServiceShip shipServiceShip = new ShipServiceShip();

        try {
            String json = shipService.get(url);
            shipServiceShip = objectMapper.readValue(json, ShipServiceShip.class);
            ship = ModelMapper.map(shipServiceShip);

            // if no exception occurred
            this.success = true;
        } catch (IOException e) {
            // json and fake failed communication
            --this.attempts;
            this.success = false;
        }

        // if JSON ShipService error received
        boolean shipServiceError = shipServiceShip.getError() != null;
        if (this.success && shipServiceError) {
            this.success = false;
        }

        return ship;
    }
}
