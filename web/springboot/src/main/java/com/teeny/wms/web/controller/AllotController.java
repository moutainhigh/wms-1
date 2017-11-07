package com.teeny.wms.web.controller;

import com.teeny.wms.app.model.KeyValueEntity;
import com.teeny.wms.app.model.ResponseEntity;
import com.teeny.wms.web.model.EmptyEntity;
import com.teeny.wms.web.model.request.AllotListRequestEntity;
import com.teeny.wms.web.model.response.AllocationEntity;
import com.teeny.wms.web.model.response.AllotEntity;
import com.teeny.wms.web.service.AllotService;
import com.teeny.wms.web.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description: 调拨单
 *
 * @author zp
 * @version 1.0
 * @see AllotController
 * @since 2017/11/7
 */
@RestController
@RequestMapping("/api/transfer")
public class AllotController {

    private AllotService mAllotService;
    private CommonService mCommonService;

    @Autowired
    public void setAllotService(AllotService allotService) {
        mAllotService = allotService;
    }

    @Autowired
    public void setCommonService(CommonService commonService) {
        mCommonService = commonService;
    }

    /**
     * 获取商品列表
     *
     * @param billNo    单据编号
     * @param goodsCode 商品编号
     * @param sId       仓库id
     * @param saId      库区id
     * @return List<AllotListEntity>
     */
    @GetMapping("/list")
    ResponseEntity<List<AllotEntity>> getList(@RequestParam("billCode") String billNo, @RequestParam("goodsCode") String goodsCode, @RequestParam("sId") int sId, @RequestParam("saId") int saId, @RequestHeader("account") String account) {
        return new ResponseEntity<>(mAllotService.getAllotList(billNo, goodsCode, sId, saId, account));
    }


    /**
     * 获取仓库
     *
     * @return List<KeyValueEntity>
     */
    @GetMapping("/warehouseList")
    ResponseEntity<List<KeyValueEntity>> getWarehouseList(@RequestHeader("account") String account) {
        return new ResponseEntity<>(mCommonService.getWarehouseList(account));
    }

    /**
     * 获取区域
     *
     * @return List<KeyValueEntity>
     */
    @GetMapping("/saList/{id}")
    ResponseEntity<List<KeyValueEntity>> getAreaList(@PathVariable("id") int warehouseId, @RequestHeader("account") String account) {
        return new ResponseEntity<>(mCommonService.getAreaList(warehouseId, account));
    }

    /**
     * 全部完成
     *
     * @return null
     */
    @PostMapping("/updateAll")
    ResponseEntity<EmptyEntity> complete(@RequestBody List<Integer> ids, @RequestHeader("account") String account) {
        mAllotService.updateAll(ids, account, 1);
        return new ResponseEntity<>();

        //TODO   user
    }

    /**
     * 单个完成
     *
     * @return null
     */
    @PostMapping("/updateOne")
    ResponseEntity<EmptyEntity> single(@RequestParam("id") int id, @RequestHeader("account") String account) {
        mAllotService.updateOne(id, account, 1);
        return new ResponseEntity<>();

        //TODO   user
    }

    /**
     * 单个完成
     *
     * @return null
     */
    @PostMapping("/update")
    ResponseEntity<EmptyEntity> update(@RequestBody AllotListRequestEntity entity, @RequestHeader("account") String account) {
        mAllotService.update(entity, account, 1);
        return new ResponseEntity<>();

        //TODO   user
    }

    /**
     * 获取货位
     *
     * @return List<AllocationEntity>
     */
    @GetMapping("/getLocations/{id}")
    ResponseEntity<List<AllocationEntity>> getLocations(@PathVariable("id") int id, @RequestHeader("account") String account) {
        return new ResponseEntity<>(mAllotService.getLocationsById(id, account));
    }

    /**
     * 获取单据
     *
     * @return List<KeyValueEntity>
     */
    @GetMapping("/billList")
    ResponseEntity<List<KeyValueEntity>> getDocumentList(@RequestParam("sId") int sId, @RequestParam("saId") int saId, @RequestHeader("account") String account) {
        return new ResponseEntity<>(mAllotService.getOrderList(saId, sId, account));
    }

    /**
     * 获取商品
     *
     * @return List<KeyValueEntity>
     */
    @GetMapping("/goodsCode")
    ResponseEntity<List<KeyValueEntity>> getGoodsList(@RequestHeader("account") String account, @RequestParam("sId") int sId, @RequestParam("saId") int saId) {
        return new ResponseEntity<>(mAllotService.getGoodsCode(account, sId, saId));
    }
}
