package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel implements IEntityModel<Long> {

  private long locationId;
  private String locationName;
  @Override
  public Long getId() {
    return locationId;
  }

  @Override
  public void setId(Long id) {
    locationId = id;
  }

}
