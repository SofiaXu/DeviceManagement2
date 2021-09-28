package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.UselessDevice;
import site.aoba.devicemanagement2api.domain.repositories.IUselessDeviceRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.UselessDeviceMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UselessDeviceModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "UselessDevice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UselessDeviceRepository implements IUselessDeviceRepository {
    private final UselessDeviceMapper mapper;

    @Override
    public List<UselessDevice> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new UselessDevice(x.getUselessDeviceId(),
                        x.getDeviceId(),
                        x.getUselessDate(),
                        x.getUselessReason(),
                        x.getUselessReporterId())).collect(Collectors.toList());
    }

    @Override
    public List<UselessDevice> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public UselessDevice getEntityById(Long id) {
        UselessDeviceModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new UselessDevice(x.getUselessDeviceId(),
                x.getDeviceId(),
                x.getUselessDate(),
                x.getUselessReason(),
                x.getUselessReporterId());
    }

    @Override
    public UselessDevice getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(UselessDevice entity) {
        mapper.updateByPrimaryKey(new UselessDeviceModel(entity.getUselessDeviceId(),
                entity.getDeviceId(),
                entity.getUselessDate(),
                entity.getUselessReason(),
                entity.getUselessReporterId()));
        clearItemCache(entity.getUselessDeviceId());
    }

    @Override
    public void createEntity(UselessDevice entity) {
        mapper.insert(new UselessDeviceModel(entity.getUselessDeviceId(),
                entity.getDeviceId(),
                entity.getUselessDate(),
                entity.getUselessReason(),
                entity.getUselessReporterId()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<UselessDevice> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UselessDevice> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
