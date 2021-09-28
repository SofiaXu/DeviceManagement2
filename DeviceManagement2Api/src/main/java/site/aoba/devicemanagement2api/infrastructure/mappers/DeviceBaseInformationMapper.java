package site.aoba.devicemanagement2api.infrastructure.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.devicemanagement2api.architecture.infrastructure.mapper.IEntityMapper;
import site.aoba.devicemanagement2api.infrastructure.models.DeviceBaseInformationModel;

@Mapper
public interface DeviceBaseInformationMapper extends IEntityMapper<Long, DeviceBaseInformationModel> {
    int countByDeviceTypeId(Long id);
}