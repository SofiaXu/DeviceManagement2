package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.DeviceBaseInformation;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceBaseInformationRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceBaseInformationMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceBaseInformationModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "DeviceBaseInformation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceBaseInformationRepository implements IDeviceBaseInformationRepository {
    private final DeviceBaseInformationMapper mapper;

    @Override
    public List<DeviceBaseInformation> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new DeviceBaseInformation(x.getId(),
                        x.getDeviceTypeId(),
                        x.getDeviceName(),
                        x.getDeviceModel(),
                        x.getDeviceCount())).collect(Collectors.toList());
    }

    @Override
    public List<DeviceBaseInformation> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public DeviceBaseInformation getEntityById(Long id) {
        DeviceBaseInformationModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new DeviceBaseInformation(x.getId(),
                x.getDeviceTypeId(),
                x.getDeviceName(),
                x.getDeviceModel(),
                x.getDeviceCount());
    }

    @Override
    public DeviceBaseInformation getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(DeviceBaseInformation entity) {
        mapper.updateByPrimaryKey(new DeviceBaseInformationModel(entity.getId(),
                entity.getDeviceTypeId(), entity.getDeviceName(), entity.getDeviceModel(),
                entity.getDeviceCount()));
        clearItemCache(entity.getId());
    }

    @Override
    public void createEntity(DeviceBaseInformation entity) {
        mapper.insert(new DeviceBaseInformationModel(entity.getId(),
                entity.getDeviceTypeId(), entity.getDeviceName(), entity.getDeviceModel(),
                entity.getDeviceCount()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<DeviceBaseInformation> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeviceBaseInformation> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
