package ebike.core.domain.model;

import ebike.core.domain.model.def.BikeType;
import ebike.core.domain.model.def.RentalBikeStatus;

public class BikeEnity implements Entity {
    private Long id;
    private BikeType type;
    private Integer price;
    private Integer currentBattery;
    private Long currentDockId;
    private Long currentRentalTxId;
    private String licensePlates;

    public BikeEnity(Long id, BikeType type, Integer price, Integer currentBattery, Long currentDock,
            Long currentRentalTxId, String licensePlates) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.currentBattery = currentBattery;
        this.currentDockId = currentDock;
        this.currentRentalTxId = currentRentalTxId;
        this.licensePlates = licensePlates;
    }

    public void rentBike(Long txId) {
        currentDockId = null;
        currentRentalTxId = txId;
    }

    public void returnBike(Long txId, Long dockId) {
        currentDockId = dockId;
        currentRentalTxId = null;
    }

    public long getDepositCost() {
        return Math.round(this.price * 0.4);
    }

    public RentalBikeStatus getStatus() {
        return currentDockId == null ? RentalBikeStatus.RENTING : RentalBikeStatus.IN_DOCKING_STATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCurrentDockId() {
        return currentDockId;
    }

    public void setCurrentDockId(Long currentDock) {
        this.currentDockId = currentDock;
    }

    public Long getCurrentRentalTxId() {
        return currentRentalTxId;
    }

    public void setCurrentRentalTx(Long currentRentalTx) {
        this.currentRentalTxId = currentRentalTx;
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
