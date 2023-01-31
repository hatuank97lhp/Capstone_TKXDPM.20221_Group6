package ebike.core.domain.service;

import ebike.core.domain.model.CreditCardEntity;
import ebike.core.domain.model.PaymentTxEntity;

public interface IBankService {
    public long getBalance(CreditCardEntity card);

    public PaymentTxEntity processTx(CreditCardEntity card, long amount);

}
