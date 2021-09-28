package site.aoba.devicemanagement2api.architecture.domain.model;

import org.springframework.data.domain.DomainEvents;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;

import java.util.Collection;

/**
 * 代表 DDD 中的聚合根
 */
public interface IAggregateRoot extends IEntity {
    @DomainEvents
    Collection<IEvent> publishEvents();
    <TEvent extends IEvent> void addEvent(TEvent event);
}
