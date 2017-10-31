package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface AreaRepository {

    @Select("SELECT a.sc_id AS id, a.name FROM ${account}.dbo.pda_Area a")
    List<CommonDTO> getAreaList(@Param("account") String account);
}
