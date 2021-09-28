package site.aoba.devicemanagement2api.domain.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class BatchAddedEvent implements IEvent {
    private final UUID id = UUID.randomUUID();
    private final Date createdTime = new Date();
    @Getter
    private final long newCount;
    @Getter
    private final long baseInformationId;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }
}
