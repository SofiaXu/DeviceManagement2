package site.aoba.devicemanagement2api.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.devicemanagement2api.architecture.infrastructure.mapper.IEntityMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceMaintainModel;

@Mapper
public interface DeviceMaintainMapper extends IEntityMapper<Long, DeviceMaintainModel> {
}