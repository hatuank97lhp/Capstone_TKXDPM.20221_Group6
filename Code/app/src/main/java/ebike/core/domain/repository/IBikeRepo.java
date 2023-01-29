package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.BikeEnity;

public interface IBikeRepo {
    public BikeEnity getById(Long id);

    public BikeEnity getCurrentRentalBikeOfUser(Long userId);

    public List<BikeEnity> getAvailableBikeInDock(Long dockId);

    public boolean save(BikeEnity bike);
}
