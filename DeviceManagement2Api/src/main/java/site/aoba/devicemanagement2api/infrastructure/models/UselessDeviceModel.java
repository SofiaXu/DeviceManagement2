package site.aoba.devicemanagement2api.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UselessDeviceModel implements IEntityModel<Long> {

  private long uselessDeviceId;
  private long deviceId;
  private Date uselessDate;
  private String uselessReason;
  private long uselessReporterId;

  @Override
  public Long getId() {
    return uselessDeviceId;
  }

  @Override
  public void setId(Long id) {
    this.uselessDeviceId = id;
  }
}
