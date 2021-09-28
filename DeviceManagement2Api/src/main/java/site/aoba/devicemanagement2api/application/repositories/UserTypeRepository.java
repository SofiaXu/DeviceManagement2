package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.UserType;
import site.aoba.devicemanagement2api.domain.repositories.IUserTypeRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.UserMapper;
import site.aoba.devicemanagement2api.infrastructure.mappers.UserTypeMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UserTypeModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "UserType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTypeRepository implements IUserTypeRepository {
    private final UserTypeMapper mapper;

    @Override
    public List<UserType> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new UserType(x.getUserTypeId(), x.getUserTypeName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserType> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public UserType getEntityById(Long id) {
        UserTypeModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new UserType(x.getUserTypeId(), x.getUserTypeName());
    }

    @Override
    public UserType getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(UserType entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createEntity(UserType entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEntity(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserType> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserType> searchEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @CacheEvict(key = "#id")
    public void clearItemCache(Long id) {

    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearItemsCache() {

    }
}
