package com.teeny.wms.web.repository;

import com.teeny.wms.web.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserRepository
 * @since 2017/10/19
 */
@Mapper
public interface UserRepository {
    UserEntity getUser(@Param("id") int id);
}
