package ebike.core.domain.repository;

import ebike.core.domain.model.UserEntity;

public interface IUserRepo {
    public UserEntity getById(Long id);
}
