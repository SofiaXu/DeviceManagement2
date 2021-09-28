package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceProviderRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceProvider;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceProviderRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceProvider")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceProviderController {
    private final IDeviceProviderRepository deviceProviderRepository;

    @GetMapping
    public JsonResponseContent<List<DeviceProvider>> getDeviceProviders(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceProviderRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceProvider> getDeviceProvider(@PathVariable Long id) {
        DeviceProvider deviceProvider = deviceProviderRepository.getEntityById(id);
        if (deviceProvider == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceProvider);
    }

    @PostMapping
    public JsonResponseContent<DeviceProvider> createDeviceProvider(@RequestBody DeviceProvider deviceProvider)
    {
        try {
            deviceProviderRepository.createEntity(deviceProvider);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceProvider);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceProvider> editDeviceProvider(@RequestBody DeviceProvider deviceProvider)
    {
        try {
            deviceProviderRepository.updateEntity(deviceProvider);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceProvider);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceProvider(@PathVariable Long id) {
        try {
            deviceProviderRepository.deleteEntity(id);
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
