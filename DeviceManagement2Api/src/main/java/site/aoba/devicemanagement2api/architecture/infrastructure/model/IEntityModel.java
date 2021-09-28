package site.aoba.devicemanagement2api.architecture.infrastructure.model;

public interface IEntityModel<TId> extends IModel {
    TId getId();
    void setId(TId id);
}
