package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceStatusRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceStatus;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceStatusRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceStatus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceStatusController {
    private final IDeviceStatusRepository deviceStatusRepository;

    @GetMapping
    public JsonResponseContent<List<DeviceStatus>> getDeviceStatuss(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceStatusRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceStatus> getDeviceStatus(@PathVariable Long id) {
        DeviceStatus deviceStatus = deviceStatusRepository.getEntityById(id);
        if (deviceStatus == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceStatus);
    }

    @PostMapping
    public JsonResponseContent<DeviceStatus> createDeviceStatus(@RequestBody DeviceStatus deviceStatus)
    {
        try {
            deviceStatusRepository.createEntity(deviceStatus);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceStatus);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceStatus> editDeviceStatus(@RequestBody DeviceStatus deviceStatus)
    {
        try {
            deviceStatusRepository.updateEntity(deviceStatus);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceStatus);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceStatus(@PathVariable Long id) {
        try {
            deviceStatusRepository.deleteEntity(id);
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
