package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.*;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceBaseInformationModel implements IEntityModel<Long> {

  private long id;
  private long deviceTypeId;
  private String deviceName;
  private String deviceModel;
  private long deviceCount;


  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
