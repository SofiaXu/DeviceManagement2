package site.aoba.devicemanagement2api.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.aoba.devicemanagement2api.domain.services.ICheckDeviceTypeDeleteService;
import site.aoba.devicemanagement2api.infrastructure.mappers.DeviceBaseInformationMapper;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckDeviceTypeDeleteService implements ICheckDeviceTypeDeleteService {
    private final DeviceBaseInformationMapper mapper;
    @Override
    public boolean check(long id) {
        return mapper.countByDeviceTypeId(id) == 0;
    }
}
