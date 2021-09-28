package site.aoba.devicemanagement2api.application.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import site.aoba.devicemanagement2api.domain.models.Employee;
import site.aoba.devicemanagement2api.domain.repositories.IEmployeeRepository;
import site.aoba.devicemanagement2api.infrastructure.mappers.EmployeeMapper;
import site.aoba.devicemanagement2api.infrastructure.models.EmployeeModel;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames = "Employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRepository implements IEmployeeRepository {
    private final EmployeeMapper mapper;
    @Override
    public List<Employee> getEntities(long pageSize, long pageIndex) {
        return mapper.selectAll(pageSize, pageIndex)
                .stream().map(x -> new Employee(x.getEmployeeId(),
                        x.getEmployeeName(),
                        x.getEmployeeGender(),
                        x.getEmployeeBirthday())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEntities(long pageSize, long pageIndex, boolean refresh) {
        return null;
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public Employee getEntityById(Long id) {
        EmployeeModel x = mapper.selectByPrimaryKey(id);
        if (x == null)
            return null;
        return new Employee(x.getEmployeeId(),
                x.getEmployeeName(),
                x.getEmployeeGender(),
                x.getEmployeeBirthday());
    }

    @Override
    public Employee getEntityById(Long id, boolean refresh) {
        if (refresh)
            clearItemCache(id);
        return getEntityById(id);
    }

    @Override
    public void updateEntity(Employee entity) {
        mapper.updateByPrimaryKey(new EmployeeModel(entity.getEmployeeId(),
                entity.getEmployeeName(),
                entity.getEmployeeGender(),
                entity.getEmployeeBirthday()));
        clearItemCache(entity.getEmployeeId());
    }

    @Override
    public void createEntity(Employee entity) {
        mapper.insert(new EmployeeModel(entity.getEmployeeId(),
                entity.getEmployeeName(),
                entity.getEmployeeGender(),
                entity.getEmployeeBirthday()));
    }

    @Override
    public void deleteEntity(Long id) {
        mapper.deleteByPrimaryKey(id);
        clearItemCache(id);
    }

    @Override
    public List<Employee> searchEntities(long pageSize, long pageIndex) {
        return null;
    }

    @Override
    public List<Employee> searchEntities(long pageSize, long pageIndex, boolean refresh) {
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
