package ebike.core.application;

import ebike.core.application.dto.output.BikeDetailOutput;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.domain.model.def.RentalBikePolicy;

public interface IBikeAppService {
    public boolean rentBike(int bikeId, RentalBikePolicy policy, int userId);

    public boolean returnBike(int bikeId, int dockingStationId);

    public BikePreviewOutput getBikePreviewByBarcode(String barcode);

    public BikeDetailOutput getCurrentRentalBikeDetailOfUser(int userId);
}
