package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.AcceptAddDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 验收
 */
@Controller
public class AcceptanceController {

    private final AcceptanceService acceptanceService;

    @Autowired
    public AcceptanceController(AcceptanceService acceptanceService) {
        this.acceptanceService = acceptanceService;
    }

    //获取单位
    @RequestMapping(value = "/api/acceptance/unit", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getUnit(@RequestHeader("account") String account, @RequestHeader("sId") int id) {
        return acceptanceService.getUnit(account, id);
    }

    //获取订单
    @RequestMapping(value = "/api/acceptance/orders/{unitId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(@PathVariable("unitId") int unitId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return acceptanceService.getOrderWithUnitId(unitId, sId, account);
    }

    //  根据billNo返回所有的数据
    @ResponseBody
    @RequestMapping(value = "/api/acceptance/detail", method = RequestMethod.GET)
    public BaseEntity<List<OrderDetailDTO>> getBillsByBillNo(@RequestParam("billNo") String billNo, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return acceptanceService.getBillsByBillNo(billNo, account, sId);
    }

    /**
     * 获取订单详情
     *
     * @param account 账套的数据库名
     * @param id      单位id
     * @return BaseEntity<List<OrderDetailDTO>>
     */
    @RequestMapping(value = "/api/acceptance/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<OrderDetailDTO>> getOrderDetailsWithUnitId(@RequestHeader("account") String account, @PathVariable("id") int id) {
        return acceptanceService.getOrderListWithUnitId(account, id);
    }

//    //一键完成
//    @RequestMapping(value = "/api/acceptance/allCompete", method = RequestMethod.POST)
//    public BaseEntity<String> allCompeteByOrderId(@RequestBody List<Integer> ids, @RequestHeader("account") String account) {
//        return acceptanceService.updateGoodsByOrderId(ids, account);
//    }

    //单个修改
    @ResponseBody
    @RequestMapping(value = "/api/acceptance/update", method = RequestMethod.POST)
    public BaseEntity competeByGoodsId(@RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return acceptanceService.updateGoodsByGoodsId(recUpdateDTO, account, user.getId());
    }

//    //单个完成
//    @ResponseBody
//    @RequestMapping(value = "/api/acceptance/updateOne", method = RequestMethod.POST)
//    public BaseEntity<String> completeOne(@RequestParam("id") int id, @RequestHeader("account") String account) {
//        return acceptanceService.completeOne(id, account);
//    }

    @ResponseBody
    @RequestMapping(value = "/api/acceptance/lots/{id}", method = RequestMethod.GET)
    public BaseEntity<List<AcceptAddDTO>> getLotList(@PathVariable("id") int id, @RequestHeader("account") String account) {
        return acceptanceService.getLotList(id, account);
    }


    //完成
    @ResponseBody
    @RequestMapping(value = "/api/acceptance/complete", method = RequestMethod.POST)
    public BaseEntity<String> compete(@RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account, @CurrentUser Employess user) {
        return acceptanceService.updateGoodsByGoodsId(recUpdateDTO, account, user.getId());
    }
}
