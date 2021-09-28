package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UselessDevice implements IEntity {
    private long uselessDeviceId;
    private long deviceId;
    private Date uselessDate;
    private String uselessReason;
    private long uselessReporterId;
}
