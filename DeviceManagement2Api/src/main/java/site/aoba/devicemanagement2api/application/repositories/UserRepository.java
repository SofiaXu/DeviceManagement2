package site.aoba.devicemanagement2api.application.repositories;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.User;
import site.aoba.devicemanagement2api.domain.models.UserType;
import site.aoba.devicemanagement2api.domain.repositories.IUserRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.UserMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@CacheConfig(cacheNames = "User")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository implements IUserRepository {
    private final UserMapper userMapper;

    @Override
    public List<User> getEntities(long pageSize, long pageIndex) {
        return userMapper.selectAll(pageSize, pageIndex)
                .stream()
                .map(x -> new User(x.getUserId(),
                        x.getUserName(),
                        x.getUserPasswordHash(),
                        x.getUserRealName(),
                        x.getUserAge(),
                        x.getUserGender(),
                        new UserType(x.getUserType().getUserTypeId(),
                                x.getUserType().getUserTypeName())))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public User getEntityById(Long id) {
        UserModel x = userMapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new User(x.getUserId(),
                x.getUserName(),
                x.getUserPasswordHash(),
                x.getUserRealName(),
                x.getUserAge(),
                x.getUserGender(),
                new UserType(x.getUserType().getUserTypeId(),
                        x.getUserType().getUserTypeName()));
    }

    @Override
    public User getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(@NonNull User entity) {
        UserModel user = toModel(entity);
        userMapper.updateByPrimaryKey(user);
        clearItemCache(entity.getUserId());
    }

    @Override
    public void createEntity(@NonNull User entity) {
        UserModel user = toModel(entity);
        userMapper.insert(user);
    }

    @Override
    public void deleteEntity(Long id) {
        userMapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    private UserModel toModel(@NonNull User entity) {
        UserModel user = new UserModel();
        user.setUserId(entity.getUserId());
        user.setUserAge(entity.getUserAge());
        user.setUserGender(entity.getUserGender());
        user.setUserName(entity.getUserName());
        user.setUserRealName(entity.getUserRealName());
        user.setUserTypeId(entity.getUserType().getUserTypeId());
        return user;
    }

    @Override
    public List<User> searchEntities(long pageSize, long pageIndex) {
        return null;
    }

    @Override
    public List<User> searchEntities(long pageSize, long pageIndex, boolean refresh) {
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "User", key = "#id")
    public void clearItemCache(Long id) {

    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearItemsCache() {

    }

    @Override
    public void changePassword(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserPasswordHash(user.getUserPasswordHash());
        userModel.setUserId(user.getUserId());
        userMapper.updatePasswordHashByPrimaryKey(userModel);
    }
}
