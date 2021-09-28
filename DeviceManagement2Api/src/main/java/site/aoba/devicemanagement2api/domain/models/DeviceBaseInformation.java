package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceBaseInformation implements IEntity {
    private long id;
    private long deviceTypeId;
    private String deviceName;
    private String deviceModel;
    private long deviceCount;
}
