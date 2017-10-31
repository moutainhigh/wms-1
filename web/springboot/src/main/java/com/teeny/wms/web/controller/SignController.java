package com.teeny.wms.web.controller;

import com.teeny.wms.app.model.ResponseEntity;
import com.teeny.wms.web.model.UserEntity;
import com.teeny.wms.web.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SignController
 * @since 2017/10/19
 */
@RestController
@RequestMapping("/sign")
public class SignController {

    private static Logger sLogger = LoggerFactory.getLogger(SignController.class);

    private final TestService mService;

    @Autowired
    public SignController(TestService service) {
        this.mService = service;
    }

    @GetMapping(value = "/in/{id}")
    public ResponseEntity<UserEntity> in(@PathVariable("id") int id) {
        sLogger.debug("enter");
        System.out.println("enter");
        UserEntity entity = mService.getUser(id);
        sLogger.debug(entity.toString());
        return new ResponseEntity<>(entity);
    }
}
