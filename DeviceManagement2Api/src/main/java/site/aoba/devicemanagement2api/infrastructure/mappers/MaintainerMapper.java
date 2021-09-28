package site.aoba.devicemanagement2api.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.devicemanagement2api.architecture.infrastructure.mapper.IEntityMapper;
import site.aoba.devicemanagement2api.infrastructure.models.MaintainerModel;

@Mapper
public interface MaintainerMapper extends IEntityMapper<Long, MaintainerModel> {

}