package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.Location;
import site.aoba.devicemanagement2api.domain.models.Maintainer;
import site.aoba.devicemanagement2api.domain.repositories.IMaintainerRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.MaintainerMapper;
import site.aoba.devicemanagement2api.infrastructure.models.MaintainerModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "Maintainer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaintainerRepository implements IMaintainerRepository {
    private final MaintainerMapper mapper;

    @Override
    public List<Maintainer> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new Maintainer(x.getMaintainerId(),
                        x.getMaintainerName(),
                        x.getMaintainerAddress(),
                        x.getMaintainerContact(),
                        x.getMaintainerTelephone())).collect(Collectors.toList());
    }

    @Override
    public List<Maintainer> getEntities(long pageSize, long pageIndex, boolean refresh) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Maintainer getEntityById(Long id) {
        MaintainerModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new Maintainer(x.getMaintainerId(),
                x.getMaintainerName(),
                x.getMaintainerAddress(),
                x.getMaintainerContact(),
                x.getMaintainerTelephone());
    }

    @Override
    public Maintainer getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(Maintainer entity) {
        mapper.updateByPrimaryKey(new MaintainerModel(entity.getMaintainerId(),
                entity.getMaintainerName(),
                entity.getMaintainerAddress(),
                entity.getMaintainerContact(),
                entity.getMaintainerTelephone()));
        clearItemCache(entity.getMaintainerId());
    }

    @Override
    public void createEntity(Maintainer entity) {
        mapper.insert(new MaintainerModel(entity.getMaintainerId(),
                entity.getMaintainerName(),
                entity.getMaintainerAddress(),
                entity.getMaintainerContact(),
                entity.getMaintainerTelephone()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Maintainer> searchEntities(long pageSize, long pageIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Maintainer> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
