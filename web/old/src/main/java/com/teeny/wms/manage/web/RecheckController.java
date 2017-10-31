package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.Putaway.RecheckCompleteDTO;
import com.teeny.wms.dto.RecipientDTO;
import com.teeny.wms.dto.ReviewDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.RecheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 复核
 */
@Controller
public class RecheckController {

    private final RecheckService recheckService;

    @Autowired
    public RecheckController(RecheckService recheckService) {
        this.recheckService = recheckService;
    }

    /**
     * 出库复核
     *
     * @param account 账套ID
     * @param billNo  订单单号
     */
    @ResponseBody
    @RequestMapping(value = "/api/recheck/exWarehouseReview/{billNo}", method = RequestMethod.GET)
    public BaseEntity<ReviewDTO> getWarehouseReview(@RequestHeader("account") String account, @PathVariable("billNo") String billNo, @CurrentUser Employess user) {
        return recheckService.getWarehouseReview(account, billNo, user.getId());
    }


    //获取单号
    @ResponseBody
    @RequestMapping(value = "/api/recheck/bills", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getBills(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return recheckService.getBills(sId, account);
    }


    @ResponseBody
    @RequestMapping(value = "/api/recheck/recipients", method = RequestMethod.GET)
    public BaseEntity<List<RecipientDTO>> getRecipients(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return recheckService.getRecipients(account, sId);
    }


    @ResponseBody
    @RequestMapping(value = "/api/recheck/complete", method = RequestMethod.POST)
    public BaseEntity<String> complete(@RequestBody RecheckCompleteDTO recheckCompleteDTO, @RequestHeader("account") String account) {
        return recheckService.complete(recheckCompleteDTO, account);
    }


//    @RequestMapping(value = "/api/recheck/completed", method = RequestMethod.POST)
//    public void updateReview(@RequestHeader("account") String account, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
//        recheckService.updateRecheckBill(account, reviewUpdateDTO);
//    }


    @ResponseBody
    @RequestMapping(value = "/api/recheck/replenishment", method = RequestMethod.GET)
    public BaseEntity<Integer> getReplenishmentCount(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return recheckService.getReplenishmentCount(account, sId);
    }

}
