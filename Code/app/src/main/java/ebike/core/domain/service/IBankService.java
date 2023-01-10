package ebike.core.domain.service;

import ebike.core.domain.dto.CreditCardDTO;

public interface IBankService {
    public Integer getBalance(CreditCardDTO card);

    public boolean subBalance(CreditCardDTO card, Number amount);
}
