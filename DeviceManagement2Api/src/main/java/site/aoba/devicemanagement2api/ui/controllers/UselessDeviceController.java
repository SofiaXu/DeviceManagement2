package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.UselessDeviceRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.UselessDevice;
import site.aoba.devicemanagement2api.domain.repositories.IUselessDeviceRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/uselessDevice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UselessDeviceController {
    private final IUselessDeviceRepository uselessDeviceRepository;

    @GetMapping
    public JsonResponseContent<List<UselessDevice>> getUselessDevices(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                uselessDeviceRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<UselessDevice> getUselessDevice(@PathVariable Long id) {
        UselessDevice uselessDevice = uselessDeviceRepository.getEntityById(id);
        if (uselessDevice == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                uselessDevice);
    }

    @PostMapping
    public JsonResponseContent<UselessDevice> createUselessDevice(@RequestBody UselessDevice uselessDevice)
    {
        try {
            uselessDeviceRepository.createEntity(uselessDevice);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    uselessDevice);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<UselessDevice> editUselessDevice(@RequestBody UselessDevice uselessDevice)
    {
        try {
            uselessDeviceRepository.updateEntity(uselessDevice);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    uselessDevice);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteUselessDevice(@PathVariable Long id) {
        try {
            uselessDeviceRepository.deleteEntity(id);
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
