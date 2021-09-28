package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceTypeRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceType;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceTypeRepository;
import site.aoba.devicemanagement2api.domain.services.ICheckDeviceTypeDeleteService;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceTypeController {
    private final IDeviceTypeRepository deviceTypeRepository;
    private final ICheckDeviceTypeDeleteService checkDeviceTypeDeleteService;

    @GetMapping
    public JsonResponseContent<List<DeviceType>> getDeviceTypes(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceTypeRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceType> getDeviceType(@PathVariable Long id) {
        DeviceType deviceType = deviceTypeRepository.getEntityById(id);
        if (deviceType == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceType);
    }

    @PostMapping
    public JsonResponseContent<DeviceType> createDeviceType(@RequestBody DeviceType deviceType)
    {
        try {
            deviceTypeRepository.createEntity(deviceType);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceType);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceType> editDeviceType(@RequestBody DeviceType deviceType)
    {
        try {
            deviceTypeRepository.updateEntity(deviceType);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceType);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceType(@PathVariable Long id) {
        try {
            if (!checkDeviceTypeDeleteService.check(id))
                return new JsonResponseContent<>(400,
                        "Bad Request",
                        new Date(),
                        "01");
            deviceTypeRepository.deleteEntity(id);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    null);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }
}
