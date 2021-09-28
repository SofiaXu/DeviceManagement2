package site.aoba.devicemanagement2api.architecture.infrastructure.mapper;

import org.apache.ibatis.annotations.Param;
import site.aoba.devicemanagement2api.architecture.infrastructure.model.IEntityModel;

import java.util.List;

public interface IEntityMapper<TId, TEntityModel extends IEntityModel<TId>> extends IMapper<TEntityModel> {
    int deleteByPrimaryKey(TId id);
    int insert(TEntityModel record);
    TEntityModel selectByPrimaryKey(TId id);
    List<TEntityModel> selectAll(@Param("pageSize") long pageSize, @Param("pageNum") long pageNum);
    int updateByPrimaryKey(TEntityModel record);
}
