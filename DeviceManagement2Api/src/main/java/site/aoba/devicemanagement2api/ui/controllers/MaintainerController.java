package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.MaintainerRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.Maintainer;
import site.aoba.devicemanagement2api.domain.repositories.IMaintainerRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/maintainer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaintainerController {
    private final IMaintainerRepository maintainerRepository;

    @GetMapping
    public JsonResponseContent<List<Maintainer>> getMaintainers(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                maintainerRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<Maintainer> getMaintainer(@PathVariable Long id) {
        Maintainer maintainer = maintainerRepository.getEntityById(id);
        if (maintainer == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                maintainer);
    }

    @PostMapping
    public JsonResponseContent<Maintainer> createMaintainer(@RequestBody Maintainer maintainer)
    {
        try {
            maintainerRepository.createEntity(maintainer);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    maintainer);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<Maintainer> editMaintainer(@RequestBody Maintainer maintainer)
    {
        try {
            maintainerRepository.updateEntity(maintainer);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    maintainer);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteMaintainer(@PathVariable Long id) {
        try {
            maintainerRepository.deleteEntity(id);
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
