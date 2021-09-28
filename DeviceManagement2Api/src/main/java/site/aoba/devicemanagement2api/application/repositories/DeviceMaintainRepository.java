package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceMaintain;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceMaintainRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceMaintainMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceMaintainModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceMaintain")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceMaintainRepository implements IDeviceMaintainRepository {
    private final DeviceMaintainMapper mapper;

    @Override
    public List<DeviceMaintain> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceMaintain(x.getDeviceMaintainId(),
                        x.getDeviceMaintainReason(),
                        x.getDeviceMaintainStartDate(),
                        x.getDeviceMaintainEndDate(),
                        x.getDeviceMaintainEmployeeId(),
                        x.getDeviceId(),
                        x.getDeviceMaintainerId(),
                        x.getDeviceMaintainPrice())).collect(Collectors.toList());
    }

    @Override
    public List<DeviceMaintain> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceMaintain getEntityById(Long id) {
        DeviceMaintainModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceMaintain(x.getDeviceMaintainId(),
                x.getDeviceMaintainReason(),
                x.getDeviceMaintainStartDate(),
                x.getDeviceMaintainEndDate(),
                x.getDeviceMaintainEmployeeId(),
                x.getDeviceId(),
                x.getDeviceMaintainerId(),
                x.getDeviceMaintainPrice());
    }

    @Override
    public DeviceMaintain getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceMaintain entity) {
        mapper.updateByPrimaryKey(new DeviceMaintainModel(entity.getDeviceMaintainId(),
                entity.getDeviceMaintainReason(),
                entity.getDeviceMaintainStartDate(),
                entity.getDeviceMaintainEndDate(),
                entity.getDeviceMaintainEmployeeId(),
                entity.getDeviceId(),
                entity.getDeviceMaintainerId(),
                entity.getDeviceMaintainPrice()));
        clearItemCache(entity.getDeviceMaintainId());
    }

    @Override
    public void createEntity(DeviceMaintain entity) {
        mapper.insert(new DeviceMaintainModel(entity.getDeviceMaintainId(),
                entity.getDeviceMaintainReason(),
                entity.getDeviceMaintainStartDate(),
                entity.getDeviceMaintainEndDate(),
                entity.getDeviceMaintainEmployeeId(),
                entity.getDeviceId(),
                entity.getDeviceMaintainerId(),
                entity.getDeviceMaintainPrice()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<DeviceMaintain> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceMaintain> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
