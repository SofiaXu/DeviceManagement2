package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceProvider implements IEntity {
    private long deviceProviderId;
    private String deviceProviderName;
}
