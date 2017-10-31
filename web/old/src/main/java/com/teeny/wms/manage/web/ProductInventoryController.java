package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.AddProductDTO;
import com.teeny.wms.dto.PdListDTO;
import com.teeny.wms.dto.ProductAddDetailDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 单品盘点
 */
@Controller
public class ProductInventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public ProductInventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //单品盘点
    @ResponseBody
    @RequestMapping(value = "/api/productsInventory/getList", method = RequestMethod.GET)
    public BaseEntity<List<PdListDTO>> getProductsInvertoryList(@RequestHeader("sId") int sId, @RequestHeader("account") String account, @RequestParam("location") String locationCode, @RequestParam("barcode") String barcode) {
        return inventoryService.getProductsInventoryList(sId, account, locationCode, barcode);
    }

//    //商品明细
//    @ResponseBody
//    @RequestMapping(value = "/api/productsInventory/details/{id}", method = RequestMethod.GET)
//    public BaseEntity<ProductDetailsDTO> getDetails(@RequestHeader("sId") int sId, @PathVariable("id") int id, @RequestHeader("account") String account) {
//       return inventoryService.getDetailsById(id, account);
//    }


    //盘点确定
    @ResponseBody
    @RequestMapping(value = "/api/productsInventory/confirm", method = RequestMethod.POST)
    public BaseEntity<String> confirm(@RequestBody List<Integer> ids, @RequestHeader("account") String account, @RequestHeader("sId") int sId, @CurrentUser Employess user) {
        return inventoryService.confirmProductPd(ids, sId, account, user.getId());
    }

    //商品修改(商品明细 保存)
    @ResponseBody
    @RequestMapping(value = "/api/productsInventory/update", method = RequestMethod.POST)
    public BaseEntity<String> update(@RequestBody AddProductDTO addProductDTO, @RequestHeader("account") String account, @RequestHeader("sId") int sId, @CurrentUser Employess user) {
        return inventoryService.addProduct(addProductDTO, account, sId, user.getId());
    }


    //新增单品查询
    @ResponseBody
    @RequestMapping(value = "/api/productsInventory/detail", method = RequestMethod.GET)
    public BaseEntity<ProductAddDetailDTO> getProductDetail(@RequestParam("goodsCode") String goodsCode, @RequestHeader("account") String account) {
        return inventoryService.getDetailsByNameAndStandard(goodsCode, account);
    }


    //新增单品
    @ResponseBody
    @RequestMapping(value = "/api/productsInventory/add", method = RequestMethod.PUT)
    public BaseEntity<String> addProduct(@RequestBody AddProductDTO addProductDTO, @RequestHeader("account") String account, @RequestHeader("sId") int sId, @CurrentUser Employess user) {
        return inventoryService.addProduct(addProductDTO, account, sId, user.getId());
    }


}
