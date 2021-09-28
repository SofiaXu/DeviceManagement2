package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintainer implements IEntity {
    private long maintainerId;
    private String maintainerName;
    private String maintainerAddress;
    private String maintainerContact;
    private String maintainerTelephone;
}
