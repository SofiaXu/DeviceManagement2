package site.aoba.devicemanagement2api.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.aoba.devicemanagement2api.architecture.domain.event.IEvent;
import site.aoba.devicemanagement2api.architecture.domain.model.IAggregateRoot;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements IAggregateRoot {
    private long userId;
    private String userName;
    @JsonIgnore
    private String userPasswordHash;
    private String userRealName;
    private long userAge;
    private long userGender;
    private UserType userType;

    @Override
    public Collection<IEvent> publishEvents() {
        return null;
    }

    @Override
    public <TEvent extends IEvent> void addEvent(TEvent event) {

    }
}
