package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;
import site.aoba.devicemanagement2api.architecture.domain.model.IAggregateRoot;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceType implements IAggregateRoot {
    private long deviceTypeId;
    private String deviceTypeName;

    @Override
    public Collection<IEvent> publishEvents() {
        return null;
    }

    @Override
    public <TEvent extends IEvent> void addEvent(TEvent event) {

    }
}
