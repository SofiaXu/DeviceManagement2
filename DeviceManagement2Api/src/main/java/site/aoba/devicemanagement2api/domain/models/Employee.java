package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements IEntity {
    private long employeeId;
    private String employeeName;
    private long employeeGender;
    private Date employeeBirthday;
}
