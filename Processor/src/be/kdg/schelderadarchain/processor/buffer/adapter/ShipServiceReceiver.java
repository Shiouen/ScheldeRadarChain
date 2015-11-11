package be.kdg.schelderadarchain.processor.buffer.adapter;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.kdg.se3.proxy.ShipServiceProxy;

import be.kdg.schelderadarchain.processor.buffer.dto.ShipServiceShip;
import be.kdg.schelderadarchain.processor.buffer.properties.BufferProperties;
import be.kdg.schelderadarchain.processor.model.Ship;
import be.kdg.schelderadarchain.processor.model.mapping.ModelMapper;

public final class ShipServiceReceiver {
    private int attempts;
    private boolean error;
    private boolean unavailable;

    private final ShipServiceProxy shipService;

    public ShipServiceReceiver() {
        this.attempts = BufferProperties.getShipServiceReceiverAttempts();

        this.error = false;
        this.unavailable = false;

        this.shipService = new ShipServiceProxy();
    }

    public boolean isSuccessful() { return !(this.hasError() || this.isUnavailable()); }
    public boolean hasError() { return this.error; }
    public boolean isUnavailable() { return this.unavailable; }

    public final Ship receive(Integer shipId) {
        String imo = shipId.toString();
        String baseUrl = "www.services4se3.com/shipservice";
        String url = String.format("%s/%s", baseUrl, imo);

        // receive ship info from ShipService
        Ship ship = receive(url);

        // while no successful connection and positive number of attempts left
        while (this.isUnavailable() && this.attempts > 0) {
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
            this.unavailable = false;
        } catch (IOException e) {
            // json and fake failed communication
            this.attempts--;
            this.unavailable = true;
        }

        if (shipServiceShip.getError() != null) {
            this.error = true;
        }

        if (this.isSuccessful()) {
            ship = ModelMapper.map(shipServiceShip);
        }

        return ship;
    }
}
