package ebike.core.application;

import ebike.core.application.dto.output.CurrentRentalTxOutput;

public interface IRentalTxAppService {
    public CurrentRentalTxOutput getRentalTxById(int id);
}
