package com.teeny.wms.service.impl;

import com.teeny.wms.config.WmsException;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.PdBillRepository;
import com.teeny.wms.core.repository.ProductsRepository;
import com.teeny.wms.dto.*;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final PdBillRepository pdBillRepository;
    private final ProductsRepository productsRepository;
    final
    CommonService commonService;

    @Autowired
    public InventoryServiceImpl(PdBillRepository pdBillRepository, ProductsRepository productsRepository, CommonService commonService) {
        this.pdBillRepository = pdBillRepository;
        this.productsRepository = productsRepository;
        this.commonService = commonService;
    }

    //////////////////////门店盘点//////////////////////////////////////

    /**
     * 获取门店盘点的详细数据
     *
     * @param pdType
     * @param saId
     * @param areaId
     * @param account
     * @param sId
     * @return
     */
    @Override
    public BaseEntity<List<StoreInventoryGoodsDTO>> getInventoryList(String pdType, int saId, int areaId, String account, int sId) {
        List<StoreInventoryGoodsDTO> list = pdBillRepository.getStoreInventoryList(pdType, saId, areaId, account, sId);

        pdBillRepository.updatePdaStatus(pdType, saId, areaId, account, 1);

        return new BaseEntity<List<StoreInventoryGoodsDTO>>(list);
    }

    /**
     * 完成单个
     *
     * @param originalId
     * @param account
     * @return
     */
    @Override
    public BaseEntity<Integer> completeOne(int originalId, String account, int userId) {
        pdBillRepository.completeOne(originalId, account, userId);
        int count = pdBillRepository.countByType(originalId, account);
        if (count == 0) {
            pdBillRepository.completeWithOriginalId(originalId, account);
        }
        return new BaseEntity<>(originalId);
    }

    @Override
    public BaseEntity<String> completeByParam(List<Integer> ids, String account, int userId) {
        if (ids.size() > 0) {
            for (Integer id : ids) {
                pdBillRepository.completeOne(id, account, userId);
            }
            int count = pdBillRepository.countByType(ids.get(0), account);
            if (count == 0) {
                pdBillRepository.completeWithOriginalId(ids.get(0), account);
            }
        }
        return new BaseEntity<String>();
    }


    //盘点编辑
    @Override
    public BaseEntity<String> edit(PdEditDTO pdEditDTO, String account, int userId) {

        if (pdEditDTO.getParam().size() > 0) {
            List<Integer> ids = pdBillRepository.getIdsByOriginalId(pdEditDTO.getId(), account);

            for (PdEditParamDTO dto : pdEditDTO.getParam()) {
                pdBillRepository.edit(pdEditDTO.getId(), dto.getLotNo(), dto.getCount(), dto.getValidateDate(), account, userId);
            }
            for (Integer id : ids) {
                pdBillRepository.deleteBySmbId(id, pdEditDTO.getId(), account);
            }
        } else {
            pdBillRepository.edit(pdEditDTO.getId(), "", 0, "", account, userId);
            pdBillRepository.deleteBySmbId(pdEditDTO.getId(), pdEditDTO.getId(), account);
        }

        int count = pdBillRepository.countByType(pdEditDTO.getId(), account);
        if (count == 0) {
            pdBillRepository.completeWithOriginalId(pdEditDTO.getId(), account);
        }

//        pdBillRepository.completeOne(pdEditDTO.getId(), account);
//        if (pdEditDTO.getParam().size() > 0) {
//            for (PdEditParamDTO dto : pdEditDTO.getParam()) {
//                pdBillRepository.edit(pdEditDTO.getId(), dto.getLotNo(), dto.getCount(), dto.getValidateDate(), account);
//            }
//            int count = pdBillRepository.countByType(pdEditDTO.getId(), account);
//            if (count == 0) {
//                pdBillRepository.completeWithGoodsDetailId(pdEditDTO.getId(), account);
//            }
//        }

        return new BaseEntity<String>();
    }

    /////////////////////仓库盘点//////////////////////////////////////////

    /**
     * 仓库盘点获取详细数据
     *
     * @param pdType
     * @param saId
     * @param areaId
     * @param account
     * @param btype
     * @param dtype
     * @param sId
     * @return
     */
    @Override
    public BaseEntity<List<StroePdListDTO>> getStroeList(String pdType, int saId, int areaId, String account, int btype, int dtype, int sId) {

//        StorePdDTO storePdDTO = new StorePdDTO();
//
//        int billCount = pdBillRepository.getBillCount(goodsId, saId, areaId, locationId, pdType, 4, account, sId);
//        // 盘点总数
//        int billTotal = pdBillRepository.getBillTotal(goodsId, saId, areaId, locationId, pdType, 4, account, sId);
//
//        //商品数
//        int pcount = pdBillRepository.getPcount(goodsId, saId, areaId, locationId, pdType,1, account, sId);
//        //总商品数
//        int ptotal = pdBillRepository.getPtotal(goodsId, saId, areaId, locationId, pdType,4, account, sId);


        List<StroePdListDTO> list = pdBillRepository.getStroePdList(pdType, saId, areaId, account, btype, dtype, sId);
        pdBillRepository.updatePdaStatus(pdType, saId, areaId, account, btype);
        return new BaseEntity<List<StroePdListDTO>>(list);
    }


    ////////////////单品盘点/////////////////////////////////////
    @Override
    public BaseEntity<List<PdListDTO>> getProductsInventoryList(int sId, String account, String locationCode, String barcode) {
        List<PdListDTO> data = pdBillRepository.getProductsInventoryList(sId, account, locationCode, barcode);
        return new BaseEntity<>(data);
    }

    @Override
    public BaseEntity<ProductDetailsDTO> getDetailsById(int id, String account) {
        ProductDetailsDTO dto = pdBillRepository.getById(id, account);
        return new BaseEntity<ProductDetailsDTO>(dto);
    }

    @Override
    public BaseEntity<String> confirmProductPd(List<Integer> ids, int sId, String account, int userId) {
        for (Integer id : ids) {
            pdBillRepository.updateStatus(id, sId, account, userId);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<CommonDTO> updateProduct(int id, float count, String account) {

        pdBillRepository.updateProductStatus(id, count, account);

        return new BaseEntity<CommonDTO>();
    }

    @Override
    public BaseEntity<List<CommonDTO>> getProductsList(String goodsName, String account) {
        List<CommonDTO> list = productsRepository.findByName(goodsName, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

    @Override
    public BaseEntity<List<String>> getStandardList(String goodsName, String account) {
        List<String> list = productsRepository.getStandardList(goodsName, account);
        return new BaseEntity<List<String>>(list);
    }


    @Override
    public BaseEntity<ProductAddDetailDTO> getDetailsByNameAndStandard(String goodsCode, String account) {
        ProductAddDetailDTO data = productsRepository.getByParams(goodsCode, account);
        return new BaseEntity<ProductAddDetailDTO>(data);
    }

    /**
     * 单品盘点添加数据
     *
     * @param dto
     * @param account
     * @param sId
     * @return
     */
    @Override
    public BaseEntity<String> addProduct(AddProductDTO dto, String account, int sId, int userId) {
        int locationId = dto.locationId;
        if (locationId <= 0) {
            locationId = commonService.getLocationIdByCode(dto.locationCode, account);
            if (locationId == 0) {
                BaseEntity<String> baseEntity = new BaseEntity<String>();
                baseEntity.setMsg("找不此货位:" + dto.locationCode);
                baseEntity.setResult(1);
                throw new WmsException(baseEntity);
            }
        }
        dto.locationId = locationId;
        pdBillRepository.addProduct(dto, account, sId, userId);
        return new BaseEntity<>();
    }

    /*-----------------------------------------------------Common---------------------------------------------*/

    @Override
    public BaseEntity<List<LotDTO>> getLotList(int originalId, String account) {
        List<LotDTO> data = pdBillRepository.getLotList(originalId, account);
        return new BaseEntity<>(data);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getPdType(int type, String account, int sId) {
        return new BaseEntity<>(pdBillRepository.getPdType(account, type, sId));
    }

    @Override
    public BaseEntity addProduct(int type, InventoryAddDTO dto, String account, int sId, int userId) {
        int locationId = commonService.getLocationIdByCode(dto.locationCode, account);
        if (locationId != 0) {
            dto.locationId = locationId;
            if (type != 2) {
                dto.billState = 1;
            } else {
                dto.billState = 2;
            }

            pdBillRepository.addInventory(dto, type, account, sId, userId);
            return new BaseEntity();
        } else {
            BaseEntity baseEntity = new BaseEntity();
            baseEntity.setMsg("找不此货位:" + dto.locationCode);
            baseEntity.setResult(1);
            throw new WmsException(baseEntity);
        }
    }

    @Override
    public BaseEntity<List<InventoryGoodsDTO>> getInventoryList(int id, boolean isMerge, String account) {
        List<InventoryGoodsDTO> list = pdBillRepository.getInventoryList(id, isMerge, account);
        pdBillRepository.updateInventoryStatus(id, 1, account);
        return new BaseEntity<>(list);
    }
}
