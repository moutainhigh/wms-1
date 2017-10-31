package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.ProductAddDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface ProductsRepository {

    @Select("SELECT p.p_id AS id, p.name FROM ${account}.dbo.pda_Products p WHERE p.name LIKE concat('%',#goodsName,'%'})")
    List<CommonDTO> findByName(@Param("goodsName") String goodsName, @Param("account") String account);

    @Select("SELECT DISTINCT p.standard FROM ${account}.dbo.pda_Products p WHERE p.name=#{goodsName}")
    List<String> getStandardList(@Param("goodsName") String goodsName, @Param("account") String account);

    @Select("SELECT p.p_id AS pId, p.name AS goodsName, p.unit1Name AS unit, p.standard, p.serial_number AS number ,p.Factory AS manufacturers FROM ${account}.dbo.pda_Products p WHERE p.barcode=#{goodsCode}")
    ProductAddDetailDTO getByParams(@Param("goodsCode") String goodsCode, @Param("account") String account);
}
