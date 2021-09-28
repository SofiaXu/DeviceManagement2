package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceProvider;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceProviderRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceProviderMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceProviderModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceProvider")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceProviderRepository implements IDeviceProviderRepository {
    private final DeviceProviderMapper mapper;

    @Override
    public List<DeviceProvider> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceProvider(x.getDeviceProviderId(),
                        x.getDeviceProviderName())).collect(Collectors.toList());
    }

    @Override
    public List<DeviceProvider> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceProvider getEntityById(Long id) {
        DeviceProviderModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceProvider(x.getDeviceProviderId(),
                x.getDeviceProviderName());
    }

    @Override
    public DeviceProvider getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceProvider entity) {
        mapper.updateByPrimaryKey(new DeviceProviderModel(entity.getDeviceProviderId(), entity.getDeviceProviderName()));
        clearItemCache(entity.getDeviceProviderId());
    }

    @Override
    public void createEntity(DeviceProvider entity) {
        mapper.insert(new DeviceProviderModel(entity.getDeviceProviderId(), entity.getDeviceProviderName()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<DeviceProvider> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceProvider> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
