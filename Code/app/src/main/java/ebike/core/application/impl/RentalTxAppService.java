package ebike.core.application.impl;

import ebike.core.application.ApplicationService;
import ebike.core.application.IRentalTxAppService;
import ebike.core.application.dto.output.CurrentRentalTxOutput;
import ebike.core.domain.repository.IRentalTxRepo;
import ebike.core.domain.service.IRentalPolicyService;

public class RentalTxAppService implements ApplicationService, IRentalTxAppService {

    private IRentalTxRepo rentalTxRepo;

    private IRentalPolicyService rentalPolicyService;

    public RentalTxAppService(IRentalTxRepo rentalTxRepo, IRentalPolicyService rentalPolicyService) {
        this.rentalTxRepo = rentalTxRepo;
        this.rentalPolicyService = rentalPolicyService;
    }

    @Override
    public CurrentRentalTxOutput getRentalTxById(int id) {
        var tx = rentalTxRepo.getRentalTxById(id);
        var txOutput = new CurrentRentalTxOutput();
        txOutput.id = tx.getId();
        txOutput.fromDock = tx.getFromDock();
        txOutput.rentPolicy = tx.getRentPolicy();
        txOutput.startAt = tx.getStartAt();
        txOutput.currentCost = rentalPolicyService.estimateCurrentCost(id);
        return txOutput;
    }

}
