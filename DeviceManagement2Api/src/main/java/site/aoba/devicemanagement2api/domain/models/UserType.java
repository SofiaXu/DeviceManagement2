package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserType implements IEntity {
    private long userTypeId;
    private String userTypeName;
}
