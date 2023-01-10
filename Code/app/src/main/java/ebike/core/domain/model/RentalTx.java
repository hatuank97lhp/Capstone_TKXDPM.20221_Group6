package ebike.core.domain.model;

import java.util.Date;

import ebike.core.domain.model.def.RentalBikePolicy;

public class RentalTx {
    private Integer id;
    private Date startAt;
    private Date endAt;
    private Integer fromDock;
    private Integer toDock;
    private RentalBikePolicy rentPolicy;
    private Integer bikeId;
    private Integer userId;

    private Integer paymentTxRent;
    private Integer paymentTxReturn;

    public RentalTx(Integer id, Date startAt, Date endAt, Integer fromDock, Integer toDock, RentalBikePolicy rentPolicy,
            Integer bikeId, Integer userId, Integer paymentTxRent, Integer paymentTxReturn) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.fromDock = fromDock;
        this.toDock = toDock;
        this.rentPolicy = rentPolicy;
        this.bikeId = bikeId;
        this.userId = userId;
        this.paymentTxRent = paymentTxRent;
        this.paymentTxReturn = paymentTxReturn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Integer getFromDock() {
        return fromDock;
    }

    public void setFromDock(Integer fromDock) {
        this.fromDock = fromDock;
    }

    public Integer getToDock() {
        return toDock;
    }

    public void setToDock(Integer toDock) {
        this.toDock = toDock;
    }

    public RentalBikePolicy getRentPolicy() {
        return rentPolicy;
    }

    public void setRentPolicy(RentalBikePolicy rentPolicy) {
        this.rentPolicy = rentPolicy;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaymentTxRent() {
        return paymentTxRent;
    }

    public void setPaymentTxRent(Integer paymentTxRent) {
        this.paymentTxRent = paymentTxRent;
    }

    public Integer getPaymentTxReturn() {
        return paymentTxReturn;
    }

    public void setPaymentTxReturn(Integer paymentTxReturn) {
        this.paymentTxReturn = paymentTxReturn;
    }

}
