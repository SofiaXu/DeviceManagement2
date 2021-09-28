package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.UserRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.User;
import site.aoba.devicemanagement2api.domain.repositories.IUserRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final IUserRepository userRepository;
    private final Pbkdf2PasswordEncoder encoder;
    @CrossOrigin
    @GetMapping
    public JsonResponseContent<List<User>> getUsers(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                userRepository.getEntities(pageSize, pageIndex));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public JsonResponseContent<User> getUser(@PathVariable Long id) {
        User user = userRepository.getEntityById(id);
        if (user == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                user);
    }

    @CrossOrigin
    @PostMapping
    public JsonResponseContent<User> createUser(@RequestBody User user)
    {
        try {
            userRepository.createEntity(user);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    user);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @CrossOrigin
    @PostMapping("/changePassword/{id}")
    public JsonResponseContent<String> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        try {
            User user = new User();
            user.setUserId(id);
            user.setUserPasswordHash(encoder.encode(request.getPassword()));
            userRepository.changePassword(user);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    null);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @CrossOrigin
    @PostMapping("/edit")
    public JsonResponseContent<User> editUser(@RequestBody User user)
    {
        try {
            userRepository.updateEntity(user);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    user);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteEntity(id);
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
