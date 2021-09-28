package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceBaseInformation;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceBaseInformationRepository;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceBaseInformation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceBaseInformationController {
    private final IDeviceBaseInformationRepository deviceBaseInformationRepository;

    @GetMapping
    public JsonResponseContent<List<DeviceBaseInformation>> getDeviceBaseInformationList(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceBaseInformationRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceBaseInformation> getDeviceBaseInformation(@PathVariable Long id) {
        DeviceBaseInformation deviceBaseInformation = deviceBaseInformationRepository.getEntityById(id);
        if (deviceBaseInformation == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceBaseInformation);
    }

    @PostMapping
    public JsonResponseContent<DeviceBaseInformation> createDeviceBaseInformation(@RequestBody DeviceBaseInformation deviceBaseInformation)
    {
        try {
            deviceBaseInformationRepository.createEntity(deviceBaseInformation);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceBaseInformation);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceBaseInformation> editDeviceBaseInformation(@RequestBody DeviceBaseInformation deviceBaseInformation)
    {
        try {
            deviceBaseInformationRepository.updateEntity(deviceBaseInformation);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceBaseInformation);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceBaseInformation(@PathVariable Long id) {
        try {
            deviceBaseInformationRepository.deleteEntity(id);
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
