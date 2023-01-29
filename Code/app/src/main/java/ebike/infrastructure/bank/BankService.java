package ebike.infrastructure.bank;

import ebike.core.domain.model.CreditCardEntity;
import ebike.core.domain.model.PaymentTxEntity;
import ebike.core.domain.service.DomainService;
import ebike.core.domain.service.IBankService;
import ebike.core.domain.service.IIDGeneratorService;

public class BankService implements IBankService, DomainService {

    private long balance = 1000000;
    private IIDGeneratorService idGeneratorService;

    public BankService(IIDGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    @Override
    public long getBalance(CreditCardEntity card) {
        return balance;
    }

    @Override
    public PaymentTxEntity processTx(CreditCardEntity card, long amount) {
        if (balance < amount) {
            return null;
        }

        balance -= amount;
        return new PaymentTxEntity(idGeneratorService.getNextId(), amount, "", card.getId());
    }

}
