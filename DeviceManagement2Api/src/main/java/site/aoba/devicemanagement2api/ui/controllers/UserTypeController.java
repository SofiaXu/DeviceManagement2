package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.aoba.devicemanagement2api.application.repositories.UserTypeRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.UserType;
import site.aoba.devicemanagement2api.domain.repositories.IUserTypeRepository;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/userType")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserTypeController {
    private final IUserTypeRepository userTypeRepository;
    @CrossOrigin
    @GetMapping
    public JsonResponseContent<List<UserType>> getUsers() {
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                userTypeRepository.getEntities(10, 1));
    }
}
