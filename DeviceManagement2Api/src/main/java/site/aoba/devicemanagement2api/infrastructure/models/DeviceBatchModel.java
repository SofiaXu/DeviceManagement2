package site.aoba.devicemanagement2api.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceBatchModel implements IEntityModel<Long> {

  private long deviceBatchId;
  private long deviceBaseInformationId;
  private long deviceProviderId;
  private long devicePurchaserId;
  private Date devicePurchaseDate;
  private long deviceBatchNumber;
  private long deviceCount;
  private double devicePrice;

  @Override
  public Long getId() {
    return deviceBatchId;
  }

  @Override
  public void setId(Long id) {
    deviceBatchId = id;
  }
}
