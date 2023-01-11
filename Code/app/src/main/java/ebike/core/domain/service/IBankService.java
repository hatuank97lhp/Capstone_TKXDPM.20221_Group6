package ebike.core.domain.service;

import ebike.core.domain.model.CreditCardEntity;
import ebike.core.domain.model.PaymentTxEntity;
import ebike.core.domain.model.UserEntity;

public interface IBankService {
    public Integer getBalance(CreditCardEntity card);

    public PaymentTxEntity processTx(CreditCardEntity card, Double amount);

}
