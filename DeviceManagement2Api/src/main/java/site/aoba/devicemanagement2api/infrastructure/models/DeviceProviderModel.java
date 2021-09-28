package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceProviderModel implements IEntityModel<Long> {

  private long deviceProviderId;
  private String deviceProviderName;

  @Override
  public Long getId() {
    return deviceProviderId;
  }

  @Override
  public void setId(Long id) {
    deviceProviderId = id;
  }
}
