package site.aoba.devicemanagement2api.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel implements IEntityModel<Long> {

  private long employeeId;
  private String employeeName;
  private long employeeGender;
  private Date employeeBirthday;

  @Override
  public Long getId() {
    return employeeId;
  }

  @Override
  public void setId(Long id) {
    employeeId = id;
  }
}
