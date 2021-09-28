package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.Location;
import site.aoba.devicemanagement2api.domain.repositories.ILocationRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.LocationMapper;
import site.aoba.devicemanagement2api.infrastructure.models.LocationModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "Location")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationRepository implements ILocationRepository {
    private final LocationMapper mapper;

    @Override
    public List<Location> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new Location(x.getLocationId(), x.getLocationName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Location> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Location getEntityById(Long id) {
        LocationModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new Location(x.getLocationId(), x.getLocationName());
    }

    @Override
    public Location getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(Location entity) {
        mapper.updateByPrimaryKey(new LocationModel(entity.getLocationId(), entity.getLocationName()));
        clearItemCache(entity.getLocationId());
    }

    @Override
    public void createEntity(Location entity) {
        mapper.insert(new LocationModel(entity.getLocationId(), entity.getLocationName()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Location> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Location> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
