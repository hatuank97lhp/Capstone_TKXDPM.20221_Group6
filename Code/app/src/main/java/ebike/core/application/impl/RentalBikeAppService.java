package ebike.core.application.impl;

import ebike.core.application.ApplicationService;
import ebike.core.application.IBikeAppService;
import ebike.core.application.dto.output.BikeDetailOutput;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.application.dto.output.CurrentRentalTxOutput;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.model.def.RentalBikeStatus;
import ebike.core.domain.repository.IBikeRepo;
import ebike.core.domain.repository.IRentalTxRepo;
import ebike.core.domain.service.IBarcodeService;

public class RentalBikeAppService implements ApplicationService, IBikeAppService {

    private IBikeRepo rentalBikeRepo;
    private IBarcodeService barcodeService;
    private IRentalTxRepo rentalTxRepo;

    public RentalBikeAppService(IBikeRepo rentalBikeRepo, IBarcodeService barcodeService,
            IRentalTxRepo rentalTxRepo) {
        this.rentalBikeRepo = rentalBikeRepo;
        this.barcodeService = barcodeService;
        this.rentalTxRepo = rentalTxRepo;
    }

    @Override
    public boolean rentBike(int bikeId, RentalBikePolicy policy) {
        var bike = rentalBikeRepo.getById(bikeId);

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
    public BikePreviewOutput getBikePreviewByBarcode(String barcode) {
        var bikeId = barcodeService.translateBarcodeToInteger(barcode);

        if (bikeId == null) {
            return null;
        }

        var bike = rentalBikeRepo.getById(bikeId);
        if (bike == null) {
            return null;
        }

        var b = new BikePreviewOutput();
        b.id = bike.getId();
        b.currentBattery = bike.getCurrentBattery();
        b.licensePlates = bike.getLicensePlates();
        b.type = bike.getType();
        b.depositCost = bike.getDepositCost();
        b.status = bike.getStatus();

        return b;
    }

    @Override
    public BikeDetailOutput getCurrentRentalBikeDetailOfUser(int userId) {
        var bike = rentalBikeRepo.getCurrentRentalBikeOfUser(userId);

        if (bike == null) {
            return null;
        }

        var b = new BikeDetailOutput();

        b.id = bike.getId();
        b.currentBattery = bike.getCurrentBattery();
        b.licensePlates = bike.getLicensePlates();
        b.type = bike.getType();
        b.depositCost = bike.getDepositCost();
        b.status = bike.getStatus();

        var txId = bike.getCurrentRentalTx();

        if (txId != null) {
            var tx = rentalTxRepo.getRentalTxById(txId);
            b.currentRentalTxOutput = new CurrentRentalTxOutput();
            b.currentRentalTxOutput.id = tx.getId();
            b.currentRentalTxOutput.currentCost = tx.estimateCurrentCost();
            b.currentRentalTxOutput.fromDock = tx.getFromDock();
            b.currentRentalTxOutput.startAt = tx.getStartAt();
            b.currentRentalTxOutput.rentPolicy = tx.getRentPolicy();
        }

        return b;
    }
}
