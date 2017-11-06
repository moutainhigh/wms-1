package com.teeny.wms.web.controller;

import com.teeny.wms.app.model.ResponseEntity;
import com.teeny.wms.app.model.StringMapEntity;
import com.teeny.wms.web.model.EmptyEntity;
import com.teeny.wms.web.model.UserEntity;
import com.teeny.wms.web.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SignController
 * @since 2017/10/19
 */
@RestController
@RequestMapping("/log")
public class SignController {

    private static Logger sLogger = LoggerFactory.getLogger(SignController.class);

    private SignService mSignService;

    @Autowired
    public void setSignService(SignService signService) {
        this.mSignService = signService;
    }

    @PostMapping(value = "/in")
    public ResponseEntity<UserEntity> in() {
        return new ResponseEntity<>();
    }


    @PostMapping(value = "/out")
    public ResponseEntity<EmptyEntity> out() {
        return new ResponseEntity<>();
    }

    // 测试接口
    @GetMapping(value = "/test")
    @ResponseBody
    public ResponseEntity<Object> test() {
        return new ResponseEntity<>();
    }

    // 获取账套
    @GetMapping(value = "/accountSets")
    @ResponseBody
    public ResponseEntity<List<StringMapEntity>> getAccountSet() {
        List<StringMapEntity> result = mSignService.getAccountSets();
        return new ResponseEntity<>(result);
    }
}
