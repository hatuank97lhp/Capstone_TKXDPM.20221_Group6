package ebike.core.domain.model;

import java.time.Instant;

import ebike.core.domain.model.def.RentalBikePolicy;

public class RentalTxEntity implements Entity {
    private Long id;
    private Instant startAt;
    private Instant endAt;
    private Long fromDockId;
    private Long toDockId;
    private RentalBikePolicy rentPolicy;
    private Long bikeId;
    private Long userId;

    private Long paymentTxRentId;
    private Long paymentTxReturnId;

    public RentalTxEntity(Long id, Instant startAt, Instant endAt, Long fromDock, Long toDock,
            RentalBikePolicy rentPolicy,
            Long bikeId, Long userId, Long paymentTxRent, Long paymentTxReturn) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.fromDockId = fromDock;
        this.toDockId = toDock;
        this.rentPolicy = rentPolicy;
        this.bikeId = bikeId;
        this.userId = userId;
        this.paymentTxRentId = paymentTxRent;
        this.paymentTxReturnId = paymentTxReturn;
    }

    public RentalTxEntity() {
    }

    public void updateRentInfo(Long dockId, RentalBikePolicy policy, Long bikeId, Long userId, Long paymentId) {
        this.startAt = Instant.now();
        this.fromDockId = dockId;
        this.rentPolicy = policy;
        this.bikeId = bikeId;
        this.userId = userId;
        this.paymentTxRentId = paymentId;
    }

    public void updateReturnInfo(Long dockId, Long paymentId) {
        this.endAt = Instant.now();
        this.toDockId = dockId;
        this.paymentTxReturnId = paymentId;
    }

    public double estimateCurrentCost() {
        return 0;
    }

    public Long getStartAtInSecond() {
        if (this.startAt == null) {
            return null;
        }
        return this.startAt.getEpochSecond();
    }

    public Long getEndAtInSecond() {
        if (this.endAt == null) {
            return null;
        }
        return this.endAt.getEpochSecond();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public Long getFromDockId() {
        return fromDockId;
    }

    public void setFromDock(Long fromDock) {
        this.fromDockId = fromDock;
    }

    public Long getToDockId() {
        return toDockId;
    }

    public void setToDock(Long toDock) {
        this.toDockId = toDock;
    }

    public RentalBikePolicy getRentPolicy() {
        return rentPolicy;
    }

    public void setRentPolicy(RentalBikePolicy rentPolicy) {
        this.rentPolicy = rentPolicy;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentTxRentId() {
        return paymentTxRentId;
    }

    public void setPaymentTxRent(Long paymentTxRent) {
        this.paymentTxRentId = paymentTxRent;
    }

    public Long getPaymentTxReturnId() {
        return paymentTxReturnId;
    }

    public void setPaymentTxReturn(Long paymentTxReturn) {
        this.paymentTxReturnId = paymentTxReturn;
    }

}
