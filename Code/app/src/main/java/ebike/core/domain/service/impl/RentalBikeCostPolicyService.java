package ebike.core.domain.service.impl;

import java.time.Instant;

import ebike.core.domain.model.BikeEnity;
import ebike.core.domain.model.RentalTxEntity;
import ebike.core.domain.model.UserEntity;
import ebike.core.domain.model.def.BikeType;
import ebike.core.domain.service.DomainService;
import ebike.core.domain.service.IRentalBikeCostPolicyService;

public class RentalBikeCostPolicyService implements DomainService, IRentalBikeCostPolicyService {

    private long minute = 60L;

    @Override
    public long estimateCurrentCost(BikeEnity bike, UserEntity user, RentalTxEntity tx) {
        var timeUsed = Instant.now().getEpochSecond() - tx.getStartAt().getEpochSecond();
        if (timeUsed <= 10 * minute) {
            return 0;
        }

        var cost = 0L;

        var firstCost = 10000;
        var secondCost = 3000;

        if (timeUsed <= 30 * minute) {
            cost = firstCost;
        } else {
            cost = firstCost + Math.round(Math.ceil((timeUsed - 30 * minute) / (15 * minute))) * secondCost;
        }

        if (bike.getType() == BikeType.ELETRIC_SIGNLE_BIKE || bike.getType() == BikeType.DOUBLE_BIKE) {
            cost = Math.round(Math.ceil(cost * 1.5));
        }

        return cost;
    }

    @Override
    public long estimateReturnCost(BikeEnity bike, UserEntity user, RentalTxEntity tx) {
        return estimateCurrentCost(bike, user, tx) - bike.getDepositCost();
    }

}
