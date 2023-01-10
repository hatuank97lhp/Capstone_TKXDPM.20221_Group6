package ebike.core.application;

import java.util.List;

import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.domain.model.def.RentalBikePolicy;

public interface IRentalBikeAppService {
    public boolean rentBike(String barcode, RentalBikePolicy policy);

    public boolean returnBike(int bikeId, int dockingStationId);

    public List<BikePreviewOutput> getListRentalBikeInDockingStation(int dockId);

    public BikePreviewOutput getRentalBikeById(int id);

    public BikePreviewOutput getCurrentRentalBikeOfUser(int userId);
}
