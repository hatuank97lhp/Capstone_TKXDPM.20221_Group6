package ebike.core.base;

import java.util.Date;

import ebike.core.def.RentalTxStatus;

public interface RentalTx {
    public int getId();

    public User getUser();

    public RentalVehicle getRentalVehicle();

    public DockingStation getFromDock();

    public DockingStation getToDock();

    public Date getStartAt();

    public Date getEndAt();

    public RentalTxStatus getRentalTxStatus();

    public PaymentTx getRentVehiclePaymentTx();

    public PaymentTx getReturnVehiclePaymentTx();
}
