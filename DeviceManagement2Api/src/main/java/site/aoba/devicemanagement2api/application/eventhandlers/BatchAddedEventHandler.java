package site.aoba.devicemanagement2api.application.eventhandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;
import site.aoba.devicemanagement2api.architecture.application.eventhandler.IEventHandler;
import site.aoba.devicemanagement2api.domain.events.BatchAddedEvent;
import site.aoba.devicemanagement2api.domain.models.DeviceBaseInformation;
import site.aoba.devicemanagement2api.domain.repositories.IDeviceBaseInformationRepository;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BatchAddedEventHandler implements IEventHandler<BatchAddedEvent> {
    private final IDeviceBaseInformationRepository deviceBaseInformationRepository;

    @Override
    @Async
    @TransactionalEventListener(BatchAddedEvent.class)
    public void doHandle(BatchAddedEvent event) {
        DeviceBaseInformation baseInformation = deviceBaseInformationRepository.getEntityById(event.getBaseInformationId());
        baseInformation.setDeviceCount(baseInformation.getDeviceCount() + event.getNewCount());
        deviceBaseInformationRepository.updateEntity(baseInformation);
    }
}
