package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceType;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceTypeRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceTypeMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceTypeModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceTypeRepository implements IDeviceTypeRepository {
    private final DeviceTypeMapper mapper;

    @Override
    public List<DeviceType> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceType(x.getDeviceTypeId(), x.getDeviceTypeName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceType> getEntities(long pageSize, long pageIndex, boolean refresh) {
        return null;
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceType getEntityById(Long id) {
        DeviceTypeModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceType(x.getDeviceTypeId(), x.getDeviceTypeName());
    }

    @Override
    public DeviceType getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceType entity) {
        mapper.updateByPrimaryKey(new DeviceTypeModel(entity.getDeviceTypeId(),
                entity.getDeviceTypeName()));
        clearItemCache(entity.getDeviceTypeId());
    }

    @Override
    public void createEntity(DeviceType entity) {
        mapper.updateByPrimaryKey(new DeviceTypeModel(entity.getDeviceTypeId(),
                entity.getDeviceTypeName()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<DeviceType> searchEntities(long pageSize, long pageIndex) {
        return null;
    }

    @Override
    public List<DeviceType> searchEntities(long pageSize, long pageIndex, boolean refresh) {
        return null;
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
