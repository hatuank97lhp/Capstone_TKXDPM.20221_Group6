package ebike.core.domain.service;

import ebike.core.domain.model.BikeEnity;
import ebike.core.domain.model.RentalTxEntity;
import ebike.core.domain.model.UserEntity;

public interface IRentalBikeCostPolicyService {
    public long estimateCurrentCost(BikeEnity bike, UserEntity user, RentalTxEntity tx);

    public long estimateReturnCost(BikeEnity bike, UserEntity user, RentalTxEntity tx);
}
