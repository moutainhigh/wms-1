package com.teeny.wms.web.controller;

import com.teeny.wms.app.model.ResponseEntity;
import com.teeny.wms.web.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/in/{id}")
    public ResponseEntity<UserEntity> in(@PathVariable(value = "id", required = false) int id) {
        return new ResponseEntity<>();
    }
}
