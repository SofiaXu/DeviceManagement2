package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceBatch;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceBatchRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceBatchMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceBatchModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceBatch")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceBatchRepository implements IDeviceBatchRepository {
    private final DeviceBatchMapper mapper;

    @Override
    public List<DeviceBatch> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceBatch(x.getDeviceBatchId(),
                        x.getDeviceBaseInformationId(),
                        x.getDeviceProviderId(),
                        x.getDevicePurchaserId(),
                        x.getDevicePurchaseDate(),
                        x.getDeviceBatchNumber(),
                        x.getDeviceCount(),
                        x.getDevicePrice())).collect(Collectors.toList());
    }

    @Override
    public List<DeviceBatch> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceBatch getEntityById(Long id) {
        DeviceBatchModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceBatch(x.getDeviceBatchId(),
                x.getDeviceBaseInformationId(),
                x.getDeviceProviderId(),
                x.getDevicePurchaserId(),
                x.getDevicePurchaseDate(),
                x.getDeviceBatchNumber(),
                x.getDeviceCount(),
                x.getDevicePrice());
    }

    @Override
    public DeviceBatch getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceBatch entity) {
        mapper.updateByPrimaryKey(new DeviceBatchModel(entity.getDeviceBatchId(),
                entity.getDeviceBaseInformationId(),
                entity.getDeviceProviderId(),
                entity.getDevicePurchaserId(),
                entity.getDevicePurchaseDate(),
                entity.getDeviceBatchNumber(),
                entity.getDeviceCount(),
                entity.getDevicePrice()));
        clearItemCache(entity.getDeviceBatchId());
    }

    @Override
    public void createEntity(DeviceBatch entity) {
        mapper.insert(new DeviceBatchModel(entity.getDeviceBatchId(),
                entity.getDeviceBaseInformationId(),
                entity.getDeviceProviderId(),
                entity.getDevicePurchaserId(),
                entity.getDevicePurchaseDate(),
                entity.getDeviceBatchNumber(),
                entity.getDeviceCount(),
                entity.getDevicePrice()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<DeviceBatch> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceBatch> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
