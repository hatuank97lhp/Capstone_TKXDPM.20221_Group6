package ebike.core.domain.repository;

import ebike.core.domain.model.CreditCardEntity;

public interface ICreditCardRepo {
    public CreditCardEntity getById(Long id);
}
