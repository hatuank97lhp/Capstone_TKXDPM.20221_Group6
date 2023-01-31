package ebike.core.domain.repository;

import ebike.core.domain.model.RentalTxEntity;

public interface IRentalTxRepo {
    public RentalTxEntity getById(Long txId);

    public boolean save(RentalTxEntity tx);
}
