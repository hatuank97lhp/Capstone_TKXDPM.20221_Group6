package ebike.core.application.impl;

import ebike.core.application.ApplicationService;
import ebike.core.application.IBikeAppService;
import ebike.core.application.dto.output.BikeDetailOutput;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.application.dto.output.CurrentRentalTxOutput;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.model.def.RentalBikeStatus;
import ebike.core.domain.repository.IBikeRepo;
import ebike.core.domain.repository.ICreditCardRepo;
import ebike.core.domain.repository.IDockingStationRepo;
import ebike.core.domain.repository.IPaymentTxRepo;
import ebike.core.domain.repository.IRentalTxRepo;
import ebike.core.domain.repository.IUserRepo;
import ebike.core.domain.service.IBankService;
import ebike.core.domain.service.IBarcodeService;
import ebike.core.domain.service.IRentalBikeCostPolicyService;
import ebike.core.domain.service.IRentalBikeService;

public class BikeAppService implements ApplicationService, IBikeAppService {

    private IBikeRepo bikeRepo;
    private IRentalTxRepo rentalTxRepo;
    private ICreditCardRepo creditCardRepo;
    private IUserRepo userRepo;
    private IPaymentTxRepo paymentTxRepo;
    private IDockingStationRepo dockingStationRepo;

    private IBarcodeService barcodeService;
    private IBankService bankService;
    private IRentalBikeService rentalBikeService;
    private IRentalBikeCostPolicyService rentalBikeCostPolicy;

    public BikeAppService(IBikeRepo bikeRepo, IRentalTxRepo rentalTxRepo, ICreditCardRepo creditCardRepo,
            IUserRepo userRepo, IPaymentTxRepo paymentTxRepo, IDockingStationRepo dockingStationRepo,
            IBarcodeService barcodeService, IBankService bankService, IRentalBikeService rentalBikeService,
            IRentalBikeCostPolicyService rentalBikeCostPolicy) {
        this.bikeRepo = bikeRepo;
        this.rentalTxRepo = rentalTxRepo;
        this.creditCardRepo = creditCardRepo;
        this.userRepo = userRepo;
        this.paymentTxRepo = paymentTxRepo;
        this.dockingStationRepo = dockingStationRepo;
        this.barcodeService = barcodeService;
        this.bankService = bankService;
        this.rentalBikeService = rentalBikeService;
        this.rentalBikeCostPolicy = rentalBikeCostPolicy;
    }

    @Override
    public boolean rentBike(Long bikeId, RentalBikePolicy policy, Long userId) {
        var bike = bikeRepo.getById(bikeId);

        if (bike == null || bike.getStatus() == RentalBikeStatus.RENTING) {
            return false;
        }

        var user = userRepo.getById(userId);

        var creditCard = creditCardRepo.getById(user.getCreditCardId());
        var paymentTx = bankService.processTx(creditCard, bike.getDepositCost());
        var dock = dockingStationRepo.getDockingStationById(bike.getCurrentDockId());
        var rentalTx = rentalBikeService.processRentBike(bike, user, dock, policy, paymentTx);

        paymentTxRepo.save(paymentTx);
        rentalTxRepo.save(rentalTx);
        bikeRepo.save(bike);
        dockingStationRepo.save(dock);

        return true;
    }

    @Override
    public boolean returnBike(Long bikeId, Long dockingStationId) {
        var bike = bikeRepo.getById(bikeId);

        if (bike == null || bike.getStatus() != RentalBikeStatus.RENTING) {
            return false;
        }

        var rentalTx = rentalTxRepo.getById(bike.getCurrentRentalTxId());
        var user = userRepo.getById(rentalTx.getUserId());

        var creditCard = creditCardRepo.getById(user.getCreditCardId());
        var paymentTx = bankService.processTx(creditCard,
                rentalBikeCostPolicy.estimateReturnCost(bike, user, rentalTx));
        var dock = dockingStationRepo.getDockingStationById(dockingStationId);

        rentalBikeService.processReturnBike(bike, dock, rentalTx, paymentTx);

        paymentTxRepo.save(paymentTx);
        rentalTxRepo.save(rentalTx);
        bikeRepo.save(bike);
        dockingStationRepo.save(dock);

        return true;
    }

    @Override
    public BikePreviewOutput getBikePreviewByBarcode(String barcode) {
        var bikeId = barcodeService.translateBarcodeToID(barcode);

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
    public BikeDetailOutput getCurrentRentalBikeDetailOfUser(Long userId) {
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

        var txId = bike.getCurrentRentalTxId();

        var tx = rentalTxRepo.getById(txId);
        b.currentRentalTxOutput = new CurrentRentalTxOutput();
        b.currentRentalTxOutput.id = tx.getId();
        b.currentRentalTxOutput.currentCost = tx.estimateCurrentCost();
        b.currentRentalTxOutput.fromDock = tx.getFromDockId();
        b.currentRentalTxOutput.startAt = tx.getStartAt();
        b.currentRentalTxOutput.rentPolicy = tx.getRentPolicy();

        return b;
    }
}
