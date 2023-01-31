package ebike.core.application;

import ebike.core.application.dto.output.UserPreviewOutput;

public interface IUserAppService {
    public UserPreviewOutput getById(long id);
}
