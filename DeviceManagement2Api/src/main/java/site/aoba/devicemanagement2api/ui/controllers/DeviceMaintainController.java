package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceMaintainRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceMaintain;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceMaintainRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceMaintain")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceMaintainController {
    private final IDeviceMaintainRepository deviceMaintainRepository;

    @GetMapping
    public JsonResponseContent<List<DeviceMaintain>> getDeviceMaintains(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceMaintainRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceMaintain> getDeviceMaintain(@PathVariable Long id) {
        DeviceMaintain deviceMaintain = deviceMaintainRepository.getEntityById(id);
        if (deviceMaintain == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceMaintain);
    }

    @PostMapping
    public JsonResponseContent<DeviceMaintain> createDeviceMaintain(@RequestBody DeviceMaintain deviceMaintain)
    {
        try {
            deviceMaintainRepository.createEntity(deviceMaintain);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceMaintain);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceMaintain> editDeviceMaintain(@RequestBody DeviceMaintain deviceMaintain)
    {
        try {
            deviceMaintainRepository.updateEntity(deviceMaintain);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceMaintain);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceMaintain(@PathVariable Long id) {
        try {
            deviceMaintainRepository.deleteEntity(id);
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
