package site.aoba.devicemanagement2api.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.aoba.devicemanagement2api.application.repositories.EmployeeRepository;
import site.aoba.devicemanagement2api.architecture.ui.dto.JsonResponseContent;
import site.aoba.devicemanagement2api.domain.models.Employee;
import site.aoba.devicemanagement2api.domain.repositories.IEmployeeRepository;
import site.aoba.devicemanagement2api.ui.dto.requestmodels.ChangePasswordRequest;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    private final IEmployeeRepository employeeRepository;

    @GetMapping
    public JsonResponseContent<List<Employee>> getEmployees(int pageSize, int pageIndex) {
        if (pageSize <= 0)
            pageSize = 10;
        if (pageIndex <= 0)
            pageIndex = 1;
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                employeeRepository.getEntities(pageSize, pageIndex));
    }

    @GetMapping("/{id}")
    public JsonResponseContent<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.getEntityById(id);
        if (employee == null)
            return new JsonResponseContent<>(404,
                    "Not Found",
                    new Date(),
                    null);
        return new JsonResponseContent<>(200,
                "OK",
                new Date(),
                employee);
    }

    @PostMapping
    public JsonResponseContent<Employee> createEmployee(@RequestBody Employee employee)
    {
        try {
            employeeRepository.createEntity(employee);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    employee);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @PostMapping("/edit")
    public JsonResponseContent<Employee> editEmployee(@RequestBody Employee employee)
    {
        try {
            employeeRepository.updateEntity(employee);
            return new JsonResponseContent<>(200,
                    "OK",
                    new Date(),
                    employee);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new JsonResponseContent<>(400, "Bad Request",new Date(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public JsonResponseContent<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeRepository.deleteEntity(id);
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
