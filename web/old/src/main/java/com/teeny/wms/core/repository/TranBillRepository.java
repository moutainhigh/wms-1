package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.dto.TransferListDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 */
@Repository
public interface TranBillRepository {


    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    List<TransferListDTO> getTransferList(@Param("billNo") String billNo, @Param("goodsCode") String goodsCode, @Param("sId") int sId, @Param("saId") int saId, @Param("account") String account);

    List<QueryDocumentDTO> getBill(@Param("account") String account, @Param("sId") int sId);

    @Update("UPDATE ${account}.dbo.pda_TranBill_D SET DealStates=1, wctime = getdate(), loginid = #{userId} WHERE original_id = #{id}")
    void updateOne(@Param("id") int id, @Param("account") String account, @Param("userId") int userId);

    @Select("SELECT count(*) AS total FROM ${account}.dbo.pda_TranBill_D d WHERE d.DealStates=0 AND d.bill_id = (SELECT DISTINCT d1.bill_id FROM ${account}.dbo.pda_TranBill_D d1 WHERE d1.original_id=#{id})")
    int countByDealstatus(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_TranBill SET billstates=3 WHERE billid=(SELECT d.bill_id FROM ${account}.dbo.pda_TranBill_D d WHERE d.smb_id=#{id})")
    void updateBillStatusBySmbId(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE pda_TranBill_D SET quantity=#{amount}, DealStates=1, pdastates=1 WHERE smb_id=#{id}")
    void updateDetails(@Param("id") int id, @Param("amount") float amount, @Param("account") String account);

    void copyData(@Param("id") int id, @Param("amount") float amount, @Param("locationId") int locationId, @Param("account") String account, @Param("userId") int userId);

    @Select("SELECT d.smb_id FROM ${account}.dbo.pda_TranBill_D d WHERE d.original_id=#{id}")
    List<Integer> getIdsByOriginalId(@Param("id") int id, @Param("account") String account);

    @Delete("DELETE FROM ${account}.dbo.pda_TranBill_D WHERE smb_id=#{id} AND original_id=#{originalId}")
    void deleteById(@Param("id") Integer id, @Param("originalId") int originalId, @Param("account") String account);

    @Select("SELECT d.quantity AS amount,l.loc_code AS locationCode FROM ${account}.dbo.pda_TranBill_D d, ${account}.dbo.pda_location l WHERE d.Location_id=l.l_id AND d.original_id=#{id}")
    List<LocationAndCountDTO> getLocationById(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_TranBill SET pdaReTime=getdate(),pdastates=1 WHERE billnumber = #{billNo}")
    void updateBill(@Param("billNo") String billNo, @Param("account") String account);

    List<CommonDTO> getBills(@Param("saId") int saId, @Param("sId") int sId, @Param("account") String account);

    List<CommonDTO> getGoodsCode(@Param("account") String account, @Param("sId") int sId, @Param("saId") int saId);

    @Update("UPDATE ${account}.dbo.pda_TranBill SET billstates=3 WHERE billid=(SELECT DISTINCT d.bill_id FROM ${account}.dbo.pda_TranBill_D d WHERE d.original_id=#{id})")
    void updateBillStatusByOriginalId(@Param("id") Integer id, @Param("account") String account);
}
