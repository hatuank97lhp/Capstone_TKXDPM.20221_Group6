package ebike.core.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import ebike.core.application.ApplicationService;
import ebike.core.application.IRentalBikeAppService;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.domain.model.Bike;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.model.def.RentalBikeStatus;
import ebike.core.domain.repository.IRentalBikeRepo;
import ebike.core.domain.service.IBarcodeService;

public class RentalBikeAppService implements ApplicationService, IRentalBikeAppService {

    private IRentalBikeRepo rentalBikeRepo;
    private IBarcodeService barcodeService;

    public RentalBikeAppService(IRentalBikeRepo rentalBikeRepo, IBarcodeService barcodeService) {
        this.rentalBikeRepo = rentalBikeRepo;
        this.barcodeService = barcodeService;
    }

    @Override
    public boolean rentBike(String barcode, RentalBikePolicy policy) {
        var bikeId = barcodeService.translateBarcodeToInteger(barcode);

        if (bikeId == null) {
            return false;
        }

        var bike = rentalBikeRepo.getRentalBikeById(bikeId);

        if (bike == null || bike.getStatus() == RentalBikeStatus.RENTING) {
            return false;
        }

        return true;
    }

    @Override
    public boolean returnBike(int bikeId, int dockingStationId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<BikePreviewOutput> getListRentalBikeInDockingStation(int dockId) {
        var bikes = rentalBikeRepo.getAvailableBikeInDock(dockId);

        return bikes.stream().map(bike -> {
            return mappingRentalBikeEntityToPreview(bike);
        }).collect(Collectors.toList());
    }

    @Override
    public BikePreviewOutput getRentalBikeById(int id) {
        var bike = rentalBikeRepo.getRentalBikeById(id);
        return mappingRentalBikeEntityToPreview(bike);
    }

    @Override
    public BikePreviewOutput getCurrentRentalBikeOfUser(int userId) {
        var bike = rentalBikeRepo.getCurrentRentalBikeOfUser(userId);
        return mappingRentalBikeEntityToPreview(bike);
    }

    private BikePreviewOutput mappingRentalBikeEntityToPreview(Bike bike) {
        if (bike == null) {
            return null;
        }

        var b = new BikePreviewOutput();
        b.id = bike.getId();
        b.currentBattery = bike.getCurrentBattery();
        b.licensePlates = bike.getLicensePlates();
        b.type = bike.getType();
        b.price = bike.getPrice();
        b.status = bike.getStatus();
        b.currentRentalTx = bike.getCurrentRentalTx();

        return b;
    }
}
