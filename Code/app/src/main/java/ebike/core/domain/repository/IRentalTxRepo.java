package ebike.core.domain.repository;

import ebike.core.domain.model.RentalTx;

public interface IRentalTxRepo {
    public RentalTx getRentalTxById(int id);

    public Integer create(RentalTx tx);
}
