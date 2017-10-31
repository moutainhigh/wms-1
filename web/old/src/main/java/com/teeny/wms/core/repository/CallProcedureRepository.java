package com.teeny.wms.core.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by lilei on 2017/8/2.
 */
@Repository
public interface CallProcedureRepository {

    @Select("CALL ${account}.dbo.TS_D_pdaData(#{dataType},#{dataKind},#{billId},#{loginId})")
    void CallProcedure(@Param("dataType") int dataType, @Param("dataKind") int dataKind, @Param("billId") int billId, @Param("loginId") int loginId, @Param("account") String account);
}
