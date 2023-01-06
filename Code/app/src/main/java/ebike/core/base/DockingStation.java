package ebike.core.base;

import java.util.List;

public interface DockingStation {
    public List<RentalVehicle> getAvailableRentalVehicle();

    public int countAvailableRentalVehicle();

    public int countEmptyDock();

    public int getArea();

    public int getId();

    public String getAddress();

    public String getName();
}
