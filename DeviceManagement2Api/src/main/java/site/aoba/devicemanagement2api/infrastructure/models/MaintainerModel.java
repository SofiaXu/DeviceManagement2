package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintainerModel implements IEntityModel<Long> {

  private long maintainerId;
  private String maintainerName;
  private String maintainerAddress;
  private String maintainerContact;
  private String maintainerTelephone;


  @Override
  public Long getId() {
    return maintainerId;
  }

  @Override
  public void setId(Long id) {
    maintainerId = id;
  }
}
