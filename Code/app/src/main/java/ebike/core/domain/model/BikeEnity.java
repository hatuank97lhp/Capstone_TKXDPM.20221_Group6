package ebike.core.domain.model;

import ebike.core.domain.model.def.BikeType;
import ebike.core.domain.model.def.RentalBikeStatus;

public class BikeEnity implements Entity {
    private Integer id;
    private BikeType type;
    private Integer price;
    private Integer currentBattery;
    private Integer currentDock;
    private Integer currentRentalTx;
    private String licensePlates;
    private RentalBikeStatus status;

    public BikeEnity(Integer id, BikeType type, Integer price, Integer currentBattery, Integer currentDock,
            Integer currentRentalTx, String licensePlates, RentalBikeStatus rentalBikeStatus) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.currentBattery = currentBattery;
        this.currentDock = currentDock;
        this.currentRentalTx = currentRentalTx;
        this.licensePlates = licensePlates;
        this.status = rentalBikeStatus;
    }

    public double getDepositCost() {
        return this.price * 0.4;
    }

    public RentalBikeStatus getStatus() {
        return status;
    }

    public void setStatus(RentalBikeStatus rentalBikeStatus) {
        this.status = rentalBikeStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BikeType getType() {
        return type;
    }

    public void setType(BikeType type) {
        this.type = type;
    }

    public Integer getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(Integer currentBattery) {
        this.currentBattery = currentBattery;
    }

    public Integer getCurrentDock() {
        return currentDock;
    }

    public void setCurrentDock(Integer currentDock) {
        this.currentDock = currentDock;
    }

    public Integer getCurrentRentalTx() {
        return currentRentalTx;
    }

    public void setCurrentRentalTx(Integer currentRentalTx) {
        this.currentRentalTx = currentRentalTx;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
