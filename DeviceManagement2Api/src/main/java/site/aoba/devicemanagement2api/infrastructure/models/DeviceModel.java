package site.aoba.devicemanagement2api.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceModel implements IEntityModel<Long> {

  private long deviceId;
  private long deviceBatchId;
  private long deviceStatusId;
  private long deviceLocationId;
  private long deviceUserId;

  @Override
  public Long getId() {
    return deviceId;
  }

  @Override
  public void setId(Long id) {
    this.deviceId = id;
  }
}
