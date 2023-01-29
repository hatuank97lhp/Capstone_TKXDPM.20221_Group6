package ebike.core.application;

import ebike.core.application.dto.output.BikeDetailOutput;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.domain.model.def.RentalBikePolicy;

public interface IBikeAppService {
    public boolean rentBike(Long bikeId, RentalBikePolicy policy, Long userId);

    public boolean returnBike(Long bikeId, Long dockingStationId);

    public BikePreviewOutput getBikePreviewByBarcode(String barcode);

    public BikeDetailOutput getCurrentRentalBikeDetailOfUser(Long userId);
}
