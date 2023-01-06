package ebike.core.base;

import ebike.core.def.RentalVehicleStatus;
import ebike.core.def.VehicleType;

public interface RentalVehicle {
    public int getId();

    public VehicleType getVehicleType();

    public int getCurrentBattery();

    public DockingStation getCurrentDockingStation();

    public RentalTx getRentalTx();

    public RentalVehicleStatus getStatus();

    public void rentBike();

    public void returnBike();

    public void lockBike();

    public void unlockBike();

}
