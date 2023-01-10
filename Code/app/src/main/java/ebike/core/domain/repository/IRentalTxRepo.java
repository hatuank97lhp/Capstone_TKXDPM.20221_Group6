package ebike.core.domain.repository;

import ebike.core.domain.model.RentalTxEntity;

public interface IRentalTxRepo {
    public RentalTxEntity getRentalTxById(int id);

    public Integer create(RentalTxEntity tx);
}
