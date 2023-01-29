package ebike.core.application.dto.output;

import java.time.Instant;

import ebike.core.domain.model.def.RentalBikePolicy;

public class CurrentRentalTxOutput {
    public Long id;
    public Instant startAt;
    public Double currentCost;
    public Long fromDock;
    public RentalBikePolicy rentPolicy;
}
