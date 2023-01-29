package ebike.core.domain.service.impl;

import ebike.core.domain.model.BikeEnity;
import ebike.core.domain.model.DockingStationEnity;
import ebike.core.domain.model.PaymentTxEntity;
import ebike.core.domain.model.RentalTxEntity;
import ebike.core.domain.model.UserEntity;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.service.DomainService;
import ebike.core.domain.service.IRentalBikeService;
import ebike.infrastructure.idgenerator.IDGeneratorService;

public class RentalBikeService implements DomainService, IRentalBikeService {

    private IDGeneratorService idGeneratorService;

    public RentalBikeService(IDGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public RentalTxEntity processRentBike(BikeEnity bike, UserEntity user, DockingStationEnity dock,
            RentalBikePolicy rentalBikePolicy, PaymentTxEntity payment) {
        var rentalTx = new RentalTxEntity();

        rentalTx.setId(idGeneratorService.getNextId());

        rentalTx.updateRentInfo(dock.getId(), rentalBikePolicy, bike.getId(), user.getId(), payment.getId());
        bike.rentBike(rentalTx.getId());
        dock.rentBike();

        return rentalTx;
    }

    @Override
    public boolean processReturnBike(BikeEnity bike, DockingStationEnity dock, RentalTxEntity rentTx,
            PaymentTxEntity paymentTx) {
        rentTx.updateReturnInfo(dock.getId(), paymentTx.getId());
        bike.returnBike(paymentTx.getId(), dock.getId());
        dock.returnBike();
        return true;
    }

}
