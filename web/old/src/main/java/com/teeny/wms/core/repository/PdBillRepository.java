package com.teeny.wms.core.repository;

import com.teeny.wms.dto.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 * 盘点
 */
@Repository
public interface PdBillRepository {

//    //获取已盘点数
//    int getBillCount(@Param("goodsId") int goodsId, @Param("saId") int saId, @Param("areaId") int areaId, @Param("locationId") int locationId, @Param("pdType") String pdType, @Param("type") int type, @Param("account") String account, @Param("sId") int sId);
//    //获取盘点总数
//    int getBillTotal(@Param("goodsId") int goodsId, @Param("saId") int saId, @Param("areaId") int areaId, @Param("locationId") int locationId, @Param("pdType") String type, @Param("type") int pdType, @Param("account") String account, @Param("sId") int sId);
//
//    //商品数
//    int getPcount(@Param("goodsId") int goodsId, @Param("saId") int saId, @Param("areaId") int areaId, @Param("locationId") int locationId, @Param("pdType") String type, @Param("type") int pdType, @Param("account") String account, @Param("sId") int sId);
//    //商品总数
//    int getPtotal(@Param("goodsId") int goodsId, @Param("saId") int saId, @Param("areaId") int areaId, @Param("locationId") int locationId, @Param("pdType") String type, @Param("type") int pdType, @Param("account") String account, @Param("sId") int sId);


    List<StoreInventoryGoodsDTO> getStoreInventoryList(@Param("pdType") String pdType, @Param("saId") int saId, @Param("areaId") int areaId, @Param("account") String account, @Param("sId") int sId);

    @Update({"UPDATE ${account}.dbo.pda_pdBill_D SET DealStates = 1, pdQty = EligibleQty , wctime = getdate(), loginid = #{userId} WHERE original_id = #{originalId}"})
    void completeOne(@Param("originalId") int originalId, @Param("account") String account, @Param("userId") int userId);

    //查询未盘点数
    @Select("SELECT count(*) FROM ${account}.dbo.pda_pdBill_D d WHERE d.DealStates=0 AND d.bill_id = (SELECT DISTINCT d1.bill_id FROM ${account}.dbo.pda_pdBill_D d1 WHERE d1.original_id=#{originalId})")
    int countByType(@Param("originalId") int originalId, @Param("account") String account);

    //完成bill
    @Update("UPDATE ${account}.dbo.pda_pdBill SET billstates = 3 WHERE billid = (SELECT d.bill_id FROM ${account}.dbo.pda_pdBill_D d WHERE d.smb_id = #{goodsDetailId})")
    void completeWithGoodsDetailId(@Param("goodsDetailId") int goodsDetailId, @Param("account") String account);


    /////////////////////////仓库盘点//////////////////////////

    //  根据btype 和dtype来判断是仓库初盘还是复盘
    List<StroePdListDTO> getStroePdList(@Param("pdType") String pdType, @Param("saId") int saId, @Param("areaId") int areaId, @Param("account") String account, @Param("btype") int btype, @Param("dtype") int dtype, @Param("sId") int sId);


    //盘点编辑 复制数据
    void edit(@Param("id") int id, @Param("lotNo") String lotNo, @Param("count") float count, @Param("validateDate") String validateDate, @Param("account") String account, @Param("userId") int userId);

    ///////////////单品盘点///////////////////////////

    List<PdListDTO> getProductsInventoryList(@Param("sId") int sId, @Param("account") String account, @Param("location") String locationCode, @Param("barcode") String barcode);

    //单品盘点获取数量
    //int countProductInventory(@Param("product") String product, @Param("location") String location, @Param("sId") int sId ,@Param("account") String account);

    //获取商品明细
    @Select("SELECT d.storehouse_id AS id,p.name AS goodsName, " +
            "p.serial_number AS number,l.loc_name AS location, " +
            "d.Batchno AS lotNo,d.quantity AS amount, " +
            "d.Validdate AS validateDate,p.unit1Name AS unit, " +
            "p.standard, p.Factory AS manufacturer, " +
            "d.MakeDate AS productDate, d.DealStates AS status " +
            " FROM ${account}.dbo.pda_kcpdBill_D d LEFT JOIN ${account}.dbo.pda_Products p ON p.p_id=d.p_id" +
            " LEFT JOIN ${account}.dbo.pda_location l ON l.l_id=d.Location_id WHERE d.storehouse_id=#{id}")
    ProductDetailsDTO getById(@Param("id") int id, @Param("account") String account);

    //盘点确定
    @Update("UPDATE ${account}.dbo.pda_kcpdBill_D SET DealStates=1, loginid = #{userId} WHERE storehouse_id = #{id} AND ss_id=#{id}")
    void updateStatus(@Param("id") int id, @Param("sId") int sId, @Param("account") String account, @Param("userId") int userId);

    @Update("UPDATE ${account}.dbo.pda_kcpdBill_D  SET pdqty = #{count}, DealStates=1 WHERE storehouse_id = #{id}")
    void updateProductStatus(@Param("id") int id, @Param("count") float count, @Param("account") String account);


    //添加数据
    void addProduct(@Param("item") AddProductDTO dto, @Param("account") String account, @Param("sId") int sId, @Param("userId") int userId);

    //获取批次
    @Select("SELECT CONVERT(varchar(100), d.Validdate, 23) AS validateDate, d.Batchno AS lotNo, d.EligibleQty AS count FROM ${account}.dbo.pda_pdBill_D d WHERE d.original_id = #{originalId}")
    List<LotDTO> getLotList(@Param("originalId") int originalId, @Param("account") String account);

    @Select("SELECT d.smb_id AS id FROM ${account}.dbo.pda_pdBill_D d WHERE d.p_id=(SELECT d1.p_id FROM ${account}.dbo.pda_pdBill_D d1 WHERE d1.smb_id=#{id}) AND d.bill_id=(SELECT d2.bill_id FROM ${account}.dbo.pda_pdBill_D d2 WHERE d2.smb_id=#{id})")
    List<Integer> getIdsBySmbId(@Param("id") int id, @Param("account") String account);

    @Delete("DELETE FROM ${account}.dbo.pda_pdBill_D WHERE smb_id=#{id} AND original_id=#{originalId}")
    void deleteBySmbId(@Param("id") Integer id, @Param("originalId") int originalId, @Param("account") String account);

    @Select("SELECT d.smb_id FROM ${account}.dbo.pda_pdBill_D d WHERE d.original_id=#{id}")
    List<Integer> getIdsByOriginalId(@Param("id") int id, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_pdBill SET pdastates = 2 WHERE billid = (SELECT DISTINCT d.bill_id FROM ${account}.dbo.pda_pdBill_D d WHERE d.original_id = #{originalId})")
    void completeWithOriginalId(@Param("originalId") int originalId, @Param("account") String account);

    @Update("UPDATE ${account}.dbo.pda_pdBill SET pdastates=1,pdaWrTime=getdate() WHERE a_id=#{areaId} AND sa_id=#{saId} AND pdname=#{pdType} AND billstates=#{type}")
    void updatePdaStatus(@Param("pdType") String pdType, @Param("saId") int saId, @Param("areaId") int areaId, @Param("account") String account, @Param("type") int type);

    List<CommonDTO> getPdType(@Param("account") String account, @Param("type") int type, @Param("sId") int sId);


    //添加数据
    void addInventory(@Param("item") InventoryAddDTO dto, @Param("type") int type, @Param("account") String account, @Param("sId") int sId, @Param("userId") int userId);


    //2017/10/12

    /**
     * 获取盘点数据列表
     *
     * @param id      对应字段billid
     * @param isMerge 是否合并
     * @param account 账套
     * @return
     */
    List<InventoryGoodsDTO> getInventoryList(@Param("id") int id, @Param("isMerge") boolean isMerge, @Param("account") String account);

    /**
     * 更新盘点单状态
     *
     * @param id      盘点单id
     * @param status  盘点单状态 0提供,1pda已读取,2pda已完成,3pda已回写(后台更改,前台不做)
     * @param account 账套
     */
    @Update("UPDATE ${account}.dbo.pda_pdBill SET pdastates=#{status},pdaWrTime=getdate() WHERE billid = #{id}")
    void updateInventoryStatus(@Param("id") int id, @Param("status") int status, @Param("account") String account);
}
