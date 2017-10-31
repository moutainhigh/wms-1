package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 复盘
 */
@Controller
public class ReinventoryController {


    private final InventoryService inventoryService;
    private final CommonService commonService;

    @Autowired
    public ReinventoryController(InventoryService inventoryService, CommonService commonService) {
        this.inventoryService = inventoryService;
        this.commonService = commonService;
    }

    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/getList/{pdType}/{saId}/{areaId}", method = RequestMethod.GET)
    public BaseEntity<List<StroePdListDTO>> getList(@PathVariable("pdType") String pdType, @PathVariable("saId") int saId,
                                                    @PathVariable("areaId") int areaId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return inventoryService.getStroeList(pdType, saId, areaId, account, 2, 2, sId);
    }

    //获取库区
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/saList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getSaList(@RequestHeader("account") String account) {
        return commonService.getSaList(account);
    }

    //获取区域
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/areaList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getAreaList(@RequestHeader("account") String account) {
        return commonService.getAreaList(account);
    }

    //单个完成
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/single", method = RequestMethod.POST)
    public BaseEntity<Integer> completeOne(@RequestParam("id") int originalId, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return inventoryService.completeOne(originalId, account, user.getId());
    }

    //确定
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/complete", method = RequestMethod.POST)
    public BaseEntity<String> completeByBillId(@RequestBody List<Integer> ids, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return inventoryService.completeByParam(ids, account, user.getId());
    }

    //盘点编辑
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/edit", method = RequestMethod.POST)
    public BaseEntity<String> edit(@RequestBody PdEditDTO pdEditDTO, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return inventoryService.edit(pdEditDTO, account, user.getId());
    }

    //获取批次
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/getLotList", method = RequestMethod.GET)
    public BaseEntity<List<LotDTO>> getLotList(@RequestParam("originalId") int originalId, @RequestHeader("account") String account) {
        return inventoryService.getLotList(originalId, account);
    }

    //获取盘点类型
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/pdType", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getPdType(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return inventoryService.getPdType(2, account, sId);
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/add", method = RequestMethod.PUT)
    public BaseEntity addProduct(@RequestBody InventoryAddDTO addProductDTO, @RequestHeader("account") String account, @RequestHeader("sId") int sId, @CurrentUser Employess user) {
        return inventoryService.addProduct(2, addProductDTO, account, sId, user.getId());
    }

    //2017/10/12修改
    //门店盘点
    @ResponseBody
    @RequestMapping(value = "/api/secondInventory/getList", method = RequestMethod.GET)
    public BaseEntity<List<InventoryGoodsDTO>> getInventory(@RequestParam("id") int id, @RequestHeader("account") String account) {
        return inventoryService.getInventoryList(id, false, account);
    }
}
