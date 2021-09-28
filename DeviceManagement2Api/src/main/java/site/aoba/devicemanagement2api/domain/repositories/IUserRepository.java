package site.aoba.devicemanagement2api.domain.repositories;

import site.aoba.devicemanagement2api.architecture.domain.repository.IRepository;
import site.aoba.devicemanagement2api.domain.models.User;

public interface IUserRepository extends IRepository<User, Long> {
    void changePassword(User user);
}
