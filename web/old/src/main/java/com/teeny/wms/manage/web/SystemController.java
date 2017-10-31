package com.teeny.wms.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lilei on 2017/7/19.
 */
@Controller
public class SystemController {


    //获取账套
    @RequestMapping(value = "/AccountSet", method = RequestMethod.GET)
    public void getAccountSet(Model model) {

    }
}
