package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.BikeEnity;

public interface IRentalBikeRepo {
    public BikeEnity getRentalBikeById(int id);

    public BikeEnity getCurrentRentalBikeOfUser(int userId);

    public int countNumAvailableBikeInDock(int dockId);

    public List<BikeEnity> getAvailableBikeInDock(int dockId);

    public boolean save(BikeEnity bike);
}
