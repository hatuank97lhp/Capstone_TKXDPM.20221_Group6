package ebike.core.application.impl;

import ebike.core.application.ApplicationService;
import ebike.core.application.IUserAppService;
import ebike.core.application.dto.output.UserPreviewOutput;
import ebike.core.domain.repository.ICreditCardRepo;
import ebike.core.domain.repository.IUserRepo;
import ebike.core.domain.service.IBankService;

public class UserAppService implements ApplicationService, IUserAppService {
    private ICreditCardRepo creditCardRepo;
    private IUserRepo userRepo;

    private IBankService bankService;

    public UserAppService(ICreditCardRepo creditCardRepo, IUserRepo userRepo, IBankService bankService) {
        this.creditCardRepo = creditCardRepo;
        this.userRepo = userRepo;
        this.bankService = bankService;
    }

    @Override
    public UserPreviewOutput getById(long id) {
        var user = userRepo.getById(id);
        var creditCard = creditCardRepo.getById(user.getCreditCardId());
        var balance = bankService.getBalance(creditCard);
        var out = new UserPreviewOutput();
        out.id = user.getId();
        out.fullname = user.getFullname();
        out.balance = balance;
        return out;
    }

}
