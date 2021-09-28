package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.Device;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceController {
    private final IDeviceRepository deviceRepository;

    @GetMapping
    public JsonResponseContent<List<Device>> getDevices(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<Device> getDevice(@PathVariable Long id) {
        Device device = deviceRepository.getEntityById(id);
        if (device == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                device);
    }

    @PostMapping
    public JsonResponseContent<Device> createDevice(@RequestBody Device device)
    {
        try {
            deviceRepository.createEntity(device);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    device);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<Device> editDevice(@RequestBody Device device)
    {
        try {
            deviceRepository.updateEntity(device);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    device);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDevice(@PathVariable Long id) {
        try {
            deviceRepository.deleteEntity(id);
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
