package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.AccountSetDTO;
import com.teeny.wms.dto.BillCountDTO;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.DocumentDTO;
import com.teeny.wms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 * 首页
 */
@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    // 测试接口
    @RequestMapping(value = "/log/test", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<Object> test() {
        return new BaseEntity<>();
    }

    // 获取账套
    @RequestMapping(value = "/log/accountSets", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<AccountSetDTO>> getAccountSet() {
        return homeService.getAccountSet();
    }

    // 获取所有仓库
    @ResponseBody
    @RequestMapping(value = "/api/home/warehouseList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getWarehouse(@RequestHeader("account") String account) {
        return homeService.getWarehouse(account);
    }

    //获取订单数
    @ResponseBody
    @RequestMapping(value = "/api/home/info", method = RequestMethod.GET)
    public BaseEntity<BillCountDTO> getBillCountByWarehouseType(@RequestHeader("sId") int sId, @RequestHeader("account") String account) {
        return homeService.getInfoByWarehouse(account, sId);
    }

    //单据查询
    @ResponseBody
    @RequestMapping(value = "/api/home/documentList", method = RequestMethod.GET)
    public BaseEntity<DocumentDTO> getDocumentList(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return homeService.getDocumentList(account, sId);
    }

}
