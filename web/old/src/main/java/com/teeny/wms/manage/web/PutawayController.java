package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by lilei on 2017/7/19.
 * 上架入库
 */
@Controller
public class PutawayController {


    private final PutOnBillService putOnBillService;

    @Autowired
    public PutawayController(PutOnBillService putOnBillService) {
        this.putOnBillService = putOnBillService;
    }

//    //获取单号
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/orderNoList", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getBillList(@RequestHeader("sId") int sId) {
//        return null;
//    }
//
//    //获取货位
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/allocationList/{orderNoId}", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getAllocationList(@PathVariable("orderNoId") int orderNoId) {
//        return null;
//    }
//
//    //获取商品
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/goodsList/{orderNoId}/{allocationId}", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getGoodsList(@PathVariable("orderNoId") int orderNoId, @PathVariable("allocationId") int allocationId) {
//
//        return null;
//    }

    //获取单号
    @ResponseBody
    @RequestMapping(value = "/api/shelve/billList/{saId}", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getBills(@PathVariable("saId") int saId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return putOnBillService.getBills(sId, saId, account);
    }

    //获取库区仓库下的库区
    @ResponseBody
    @RequestMapping(value = "/api/shelve/saList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getSaList(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return putOnBillService.getSaList(sId, account);
    }

    //查询
    @RequestMapping(value = "/api/shelve/goodsDetailList/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<PutawayDTO>> getPutOnBill(@PathVariable("orderNo") String orderNo, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return putOnBillService.getGoodsDetailList(orderNo, account, sId);
    }

    //快速上架
    @RequestMapping(value = "/api/shelve/all", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity<String> putOnQuickly(@RequestBody List<Integer> ids, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return putOnBillService.putOnBillQuickly(ids, account, user.getId());
    }

    //单个上架
    @RequestMapping(value = "/api/shelve/single", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity putOnWithOne(@RequestParam("id") int originalId, @RequestHeader("account") String account, @CurrentUser Employess user) {
        putOnBillService.putOnBillWithOne(originalId, account, user.getId());
        return new BaseEntity();
    }

    //修改
    @RequestMapping(value = "/api/shelve/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity updateByBdId(@RequestBody PutawayAddDTO putawayAddDTO, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return putOnBillService.updateOne(putawayAddDTO, account, user.getId());
    }


    //获取货位  详情里面的货位
    @ResponseBody
    @RequestMapping(value = "/api/shelve/locationList/{id}", method = RequestMethod.GET)
    public BaseEntity<List<LocationAndCountDTO>> getLocationList(@PathVariable("id") int id, @RequestHeader("account") String account) {
        return putOnBillService.getLocationList(id, account);
    }

    //获取上架单所有货位
    @ResponseBody
    @RequestMapping(value = "/api/shelve/locations", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getLocaions(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return putOnBillService.getLocations(sId, account);
    }

}
