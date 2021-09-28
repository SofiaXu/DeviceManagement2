package site.aoba.devicemanagement2api.infrastructure.mappers;

import org.apache.ibatis.annotations.Mapper;
import site.aoba.devicemanagement2api.architecture.infrastructure.mapper.IEntityMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UserModel;

@Mapper
public interface UserMapper extends IEntityMapper<Long, UserModel> {
    int updatePasswordHashByPrimaryKey(UserModel record);
}