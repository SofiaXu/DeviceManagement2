package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceStatus;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceStatusRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceStatusMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceStatusModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceStatus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceStatusRepository implements IDeviceStatusRepository {
    private final DeviceStatusMapper mapper;

    @Override
    public List<DeviceStatus> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceStatus(x.getDeviceStatusId(), x.getDeviceStatusName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceStatus> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceStatus getEntityById(Long id) {
        DeviceStatusModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceStatus(x.getDeviceStatusId(), x.getDeviceStatusName());
    }

    @Override
    public DeviceStatus getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceStatus entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createEntity(DeviceStatus entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteEntity(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceStatus> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceStatus> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
