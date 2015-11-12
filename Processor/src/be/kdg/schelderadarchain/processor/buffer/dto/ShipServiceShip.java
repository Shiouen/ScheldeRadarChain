package be.kdg.schelderadarchain.processor.buffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This DTO class represents a Ship within the buffer part of the processor.
 *
 * @author Olivier Van Aken
 */
public class ShipServiceShip {
    private String IMO;
    private int numberOfPassangers;
    private boolean dangereousCargo;
    private String error;
    private String description;
    private List<ShipServiceCargo> cargo;

    public ShipServiceShip() { }

    public String getIMO() { return this.IMO; }
    public int getNumberOfPassangers() { return this.numberOfPassangers; }
    public boolean getDangereousCargo() { return this.dangereousCargo; }
    public String getError() { return this.error; }
    public String getDescription() { return this.description; }
    public List<ShipServiceCargo> getCargo() { return this.cargo; }

    @JsonProperty("IMO")
    public void setIMO(String IMO) { this.IMO = IMO; }
    public void setNumberOfPassangers(int numberOfPassangers) { this.numberOfPassangers = numberOfPassangers; }
    public void setDangereousCargo(boolean dangereousCargo) { this.dangereousCargo = dangereousCargo; }
    public void setError(String error) { this.error = error; }
    public void setDescription(String description) { this.description = description; }
    public void setCargo(List<ShipServiceCargo> cargo) { this.cargo = cargo; }
}
