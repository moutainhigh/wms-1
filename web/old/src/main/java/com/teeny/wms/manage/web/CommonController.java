package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.HistoryAllocationDTO;
import com.teeny.wms.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see CommonController
 * @since 2017/9/22
 */

@Controller
@RequestMapping("/api/common")
public class CommonController {

    private CommonService mCommonService;

    @Autowired
    public CommonController(CommonService commonService) {
        mCommonService = commonService;
    }

    //获取历史货位
    @RequestMapping(value = "/historyLocation", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<HistoryAllocationDTO>> getHistoryLocation(@RequestHeader("account") String account, @RequestParam("goodsId") int pId) {
        return mCommonService.getHistoryLocation(account, pId);
    }
}
