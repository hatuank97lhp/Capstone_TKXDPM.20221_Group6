package ebike.core.domain.repository;

import ebike.core.domain.model.PaymentTxEntity;

public interface IPaymentTxRepo {
    public boolean save(PaymentTxEntity tx);
}
