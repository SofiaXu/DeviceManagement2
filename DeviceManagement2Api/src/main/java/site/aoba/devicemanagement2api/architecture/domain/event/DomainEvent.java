package site.aoba.devicemanagement2api.architecture.domain.event;

import java.util.Date;
import java.util.UUID;

/**
 * 代表 DDD 中的领域事件
 */
public abstract class DomainEvent implements IEvent {
    private UUID id;
    private Date createdTime;

    public DomainEvent() {
        id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }
}
