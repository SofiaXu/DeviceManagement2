package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatusModel implements IEntityModel<Long> {

  private long deviceStatusId;
  private String deviceStatusName;

  @Override
  public Long getId() {
    return deviceStatusId;
  }

  @Override
  public void setId(Long id) {
    deviceStatusId = id;
  }
}
