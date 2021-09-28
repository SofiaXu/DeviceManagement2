package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceMaintain implements IEntity {
    private long deviceMaintainId;
    private String deviceMaintainReason;
    private Date deviceMaintainStartDate;
    private Date deviceMaintainEndDate;
    private long deviceMaintainEmployeeId;
    private long deviceId;
    private long deviceMaintainerId;
    private double deviceMaintainPrice;
}
