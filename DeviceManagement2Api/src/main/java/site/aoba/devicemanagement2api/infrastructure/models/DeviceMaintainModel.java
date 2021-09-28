package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceMaintainModel implements IEntityModel<Long> {

  private long deviceMaintainId;
  private String deviceMaintainReason;
  private Date deviceMaintainStartDate;
  private Date deviceMaintainEndDate;
  private long deviceMaintainEmployeeId;
  private long deviceId;
  private long deviceMaintainerId;
  private double deviceMaintainPrice;


  @Override
  public Long getId() {
    return deviceMaintainerId;
  }

  @Override
  public void setId(Long id) {
    deviceMaintainerId = id;
  }
}
