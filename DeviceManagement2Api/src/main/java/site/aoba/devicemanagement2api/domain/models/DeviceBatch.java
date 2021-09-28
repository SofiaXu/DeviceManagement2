package site.aoba.devicemanagement2api.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;
import site.aoba.devicemanagement2api.architecture.domain.model.IAggregateRoot;
import site.aoba.devicemanagement2api.architecture.domain.model.IEntity;
import site.aoba.devicemanagement2api.domain.events.BatchAddedEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceBatch implements IAggregateRoot {
    private long deviceBatchId;
    private long deviceBaseInformationId;
    private long deviceProviderId;
    private long devicePurchaserId;
    private Date devicePurchaseDate;
    private long deviceBatchNumber;
    private long deviceCount;
    private double devicePrice;

    @Override
    public Collection<IEvent> publishEvents() {
        List<IEvent> list = new ArrayList<>();
        list.add(new BatchAddedEvent(deviceCount, deviceBaseInformationId));
        return list;
    }

    @Override
    public <TEvent extends IEvent> void addEvent(TEvent event) {

    }
}
