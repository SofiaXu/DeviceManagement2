package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.DeviceBatchRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.DeviceBatch;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceBatchRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/deviceBatch")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceBatchController {
    private final IDeviceBatchRepository deviceBatchRepository;

    @GetMapping
    public JsonResponseContent<List<DeviceBatch>> getDeviceBatchs(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceBatchRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<DeviceBatch> getDeviceBatch(@PathVariable Long id) {
        DeviceBatch deviceBatch = deviceBatchRepository.getEntityById(id);
        if (deviceBatch == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                deviceBatch);
    }

    @PostMapping
    public JsonResponseContent<DeviceBatch> createDeviceBatch(@RequestBody DeviceBatch deviceBatch)
    {
        try {
            deviceBatchRepository.createEntity(deviceBatch);
            deviceBatch.publishEvents();
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceBatch);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<DeviceBatch> editDeviceBatch(@RequestBody DeviceBatch deviceBatch)
    {
        try {
            deviceBatchRepository.updateEntity(deviceBatch);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    deviceBatch);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteDeviceBatch(@PathVariable Long id) {
        try {
            deviceBatchRepository.deleteEntity(id);
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
