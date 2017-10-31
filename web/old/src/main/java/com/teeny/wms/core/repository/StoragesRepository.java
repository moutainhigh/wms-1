package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 *
 */
@Repository
public interface StoragesRepository {

    @Select("SELECT s.s_id AS id, s.name FROM ${account}.dbo.pda_storages s")
    List<CommonDTO> findAll(@Param("account") String account);
}
