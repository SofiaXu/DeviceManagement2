package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatus implements IEntity {
    private long deviceStatusId;
    private String deviceStatusName;
}
