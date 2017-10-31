package com.teeny.wms.core.repository;

import com.teeny.wms.dto.HistoryAllocationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see CommonRepository
 * @since 2017/9/22
 */

@Repository
public interface CommonRepository {

    List<HistoryAllocationDTO> getHistoryLocation(@Param("account") String account, @Param("pId") int pId);
}
