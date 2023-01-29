package ebike.core.domain.service;

import ebike.core.domain.model.BikeEnity;
import ebike.core.domain.model.DockingStationEnity;
import ebike.core.domain.model.PaymentTxEntity;
import ebike.core.domain.model.RentalTxEntity;
import ebike.core.domain.model.UserEntity;
import ebike.core.domain.model.def.RentalBikePolicy;

public interface IRentalBikeService {
    public RentalTxEntity processRentBike(BikeEnity bike, UserEntity user, DockingStationEnity dock,
            RentalBikePolicy rentalBikePolicy, PaymentTxEntity payment);

    public boolean processReturnBike(BikeEnity bike, DockingStationEnity dock, RentalTxEntity rentTx,
            PaymentTxEntity paymentTx);
}
