package site.aoba.devicemanagement2api.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.devicemanagement2api.architecture.infrastructure.mapper.IEntityMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UselessDeviceModel;

@Mapper
public interface UselessDeviceMapper extends IEntityMapper<Long, UselessDeviceModel> {

}