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
public interface LocationRepository {
    @Select("SELECT l_id AS id FROM ${account}.dbo.pda_location l WHERE l.loc_code=#{locationCode}")
    int findByLocationCode(@Param("locationCode") String locationCode,@Param("account") String account);

    @Select("SELECT l.l_id AS id, loc_name AS name FROM ${account}.dbo.pda_location l WHERE l.sa_id=#{saId}")
    List<CommonDTO> getBysaId(@Param("saId") int saId, @Param("account") String account);

    @Select("SELECT DISTINCT l.l_id FROM pda_location l WHERE l.loc_code=#{locationCode}")
    Integer getIdByCode(@Param("locationCode") String locationCode,@Param("account") String account);
}
