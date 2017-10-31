package com.teeny.wms.service;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
public interface InventoryService {

    BaseEntity<List<StoreInventoryGoodsDTO>> getInventoryList(String pdType, int saId, int areaId, String account, int sId);

    BaseEntity<List<PdListDTO>> getProductsInventoryList(int sId, String account, String locationCode, String barcode);

    BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account);

    BaseEntity<String> confirmProductPd(List<Integer> ids, int sId, String account, int userId);

    BaseEntity<CommonDTO> updateProduct(int id, float count, String account);

    BaseEntity<List<CommonDTO>> getProductsList(String goodsName, String account);

    BaseEntity<List<String>> getStandardList(String goodsName, String account);

    BaseEntity<String> addProduct(AddProductDTO addProductDTO, String account, int sId, int userId);

    BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsCode, String account);

    BaseEntity<Integer> completeOne(int goodsDetailId, String account, int userId);

    BaseEntity<String> completeByParam(List<Integer> ids, String account, int userId);

    BaseEntity<List<StroePdListDTO>> getStroeList(String pdType, int saId, int areaId, String account, int btype, int dype, int sId);

    BaseEntity<String> edit(PdEditDTO pdEditDTO, String account, int userId);

    BaseEntity<List<LotDTO>> getLotList(int originalId, String account);

    BaseEntity<List<CommonDTO>> getPdType(int type, String account, int sId);

    BaseEntity addProduct(int type, InventoryAddDTO dto, String account, int sId, int userId);



    //2017/10/12
    BaseEntity<List<InventoryGoodsDTO>> getInventoryList(int id,boolean isMerge, String account);
}
