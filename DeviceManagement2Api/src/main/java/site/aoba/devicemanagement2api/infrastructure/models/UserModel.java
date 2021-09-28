package site.aoba.devicemanagement2api.infrastructure.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements IEntityModel<Long> {

  private long userId;
  private String userName;
  private String userPasswordHash;
  private String userRealName;
  private long userAge;
  private long userGender;
  private long userTypeId;
  private UserTypeModel userType;

  @Override
  public Long getId() {
    return userId;
  }

  @Override
  public void setId(Long id) {
    this.userId = id;
  }
}
