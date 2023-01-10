package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.Bike;

public interface IRentalBikeRepo {
    public Bike getRentalBikeById(int id);

    public Bike getCurrentRentalBikeOfUser(int userId);

    public int countNumAvailableBikeInDock(int dockId);

    public List<Bike> getAvailableBikeInDock(int dockId);

    public boolean save(Bike bike);
}
