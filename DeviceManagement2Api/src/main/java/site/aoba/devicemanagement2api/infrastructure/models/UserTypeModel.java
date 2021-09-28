package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeModel implements IEntityModel<Long> {

  private long userTypeId;
  private String userTypeName;

  @Override
  public Long getId() {
    return userTypeId;
  }

  @Override
  public void setId(Long id) {
    this.userTypeId = id;
  }
}
