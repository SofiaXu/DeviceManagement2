package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.Device;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "Device")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceRepository implements IDeviceRepository {
    private final DeviceMapper mapper;

    @Override
    public List<Device> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new Device(x.getDeviceId(),
                        x.getDeviceBatchId(),
                        x.getDeviceStatusId(),
                        x.getDeviceLocationId(),
                        x.getDeviceUserId())).collect(Collectors.toList());
    }

    @Override
    public List<Device> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Device getEntityById(Long id) {
        DeviceModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new Device(x.getDeviceId(),
                x.getDeviceBatchId(),
                x.getDeviceStatusId(),
                x.getDeviceLocationId(),
                x.getDeviceUserId());
    }

    @Override
    public Device getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(Device entity) {
        mapper.updateByPrimaryKey(new DeviceModel(entity.getDeviceId(),
                entity.getDeviceBatchId(),
                entity.getDeviceStatusId(),
                entity.getDeviceLocationId(),
                entity.getDeviceUserId()));
        clearItemCache(entity.getDeviceId());
    }

    @Override
    public void createEntity(Device entity) {
        mapper.insert(new DeviceModel(entity.getDeviceId(),
                entity.getDeviceBatchId(),
                entity.getDeviceStatusId(),
                entity.getDeviceLocationId(),
                entity.getDeviceUserId()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Device> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Device> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
