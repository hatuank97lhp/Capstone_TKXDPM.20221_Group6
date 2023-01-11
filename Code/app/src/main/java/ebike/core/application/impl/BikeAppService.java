package ebike.core.application.impl;

import ebike.core.application.ApplicationService;
import ebike.core.application.IBikeAppService;
import ebike.core.application.dto.output.BikeDetailOutput;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.application.dto.output.CurrentRentalTxOutput;
import ebike.core.domain.dto.CreditCardDTO;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.model.def.RentalBikeStatus;
import ebike.core.domain.repository.IBikeRepo;
import ebike.core.domain.repository.ICreditCardRepo;
import ebike.core.domain.repository.IRentalTxRepo;
import ebike.core.domain.repository.IUserRepo;
import ebike.core.domain.service.IBankService;
import ebike.core.domain.service.IBarcodeService;

public class BikeAppService implements ApplicationService, IBikeAppService {

    private IBikeRepo bikeRepo;
    private IRentalTxRepo rentalTxRepo;
    private ICreditCardRepo creditCardRepo;
    private IUserRepo userRepo;

    private IBarcodeService barcodeService;
    private IBankService bankService;

    public BikeAppService(IBikeRepo bikeRepo, IRentalTxRepo rentalTxRepo, ICreditCardRepo creditCardRepo,
            IUserRepo userRepo, IBarcodeService barcodeService, IBankService bankService) {
        this.bikeRepo = bikeRepo;
        this.rentalTxRepo = rentalTxRepo;
        this.creditCardRepo = creditCardRepo;
        this.userRepo = userRepo;
        this.barcodeService = barcodeService;
        this.bankService = bankService;
    }

    @Override
    public boolean rentBike(int bikeId, RentalBikePolicy policy, int userId) {
        var bike = bikeRepo.getById(bikeId);

        if (bike == null || bike.getStatus() == RentalBikeStatus.RENTING) {
            return false;
        }

        var user = userRepo.getById(userId);

        if (user == null || user.getCreditCardId() == null) {
            return false;
        }

        var creditCard = creditCardRepo.getById(user.getCreditCardId());

        var depositCost = bike.getDepositCost();

        var paymentTx = bankService.processTx(creditCard, depositCost);

        if (paymentTx == null) {
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

        var bike = bikeRepo.getById(bikeId);
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
        var bike = bikeRepo.getCurrentRentalBikeOfUser(userId);

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
