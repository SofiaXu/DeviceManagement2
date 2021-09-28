package site.aoba.devicemanagement2api.architecture.application.eventhandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;

public interface IEventHandler<TEvent extends IEvent> {
    void doHandle(TEvent event);
}
