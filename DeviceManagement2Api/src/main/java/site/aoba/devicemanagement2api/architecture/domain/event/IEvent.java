package site.aoba.devicemanagement2api.architecture.domain.event;

import java.util.Date;
import java.util.UUID;

/**
 * 代表 DDD 中的领域事件接口
 */
public interface IEvent {
    UUID getId();
    Date getCreatedTime();
}
