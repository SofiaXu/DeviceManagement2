package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.services.AuthenticationService;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.LoginRequest;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResponseContent<String> login(@RequestBody LoginRequest loginRequest) {
        JsonResponseContent<String> result = new JsonResponseContent<>();
        try {
            String token = authenticationService.login(loginRequest.getUserId(), loginRequest.getPassword());
            if (token == null) {
                result.setContent(null);
                result.setMessage("Not Found");
                result.setStatusCode(404);
                result.setTimestamp(new Date());
                return result;
            }
            result.setContent(token);
            result.setMessage("OK");
            result.setStatusCode(200);
            result.setTimestamp(new Date());
            return result;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result.setContent(null);
            result.setMessage("Internal Server Error");
            result.setStatusCode(500);
            result.setTimestamp(new Date());
            return result;
        }
    }
}
