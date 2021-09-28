package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTypeModel implements IEntityModel<Long> {

  private long deviceTypeId;
  private String deviceTypeName;

  @Override
  public Long getId() {
    return deviceTypeId;
  }

  @Override
  public void setId(Long id) {
    deviceTypeId = id;
  }
}
