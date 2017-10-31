package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.AcceptanceService;
import com.teeny.wms.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by QHAHA on 2017/7/8.
 */
@Controller
@Transactional
public class Test {

    private final SystemService systemService;
    private final AcceptanceService acceptanceService;

    @Autowired
    public Test(SystemService systemService, AcceptanceService acceptanceService) {
        this.systemService = systemService;
        this.acceptanceService = acceptanceService;
    }

    @RequestMapping(value = "/api/haha", method = RequestMethod.GET)
    public void haha(Model model, @CurrentUser Employess user) {
        List<String> list = new ArrayList<String>();
        list.add("ddd");
        list.add("fff");
        list.add("aaa");
        model.addAttribute("fff", list);
    }

//    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
//    public void user(Model model) {
//        List<User> list = userService.findAll();
//        model.addAttribute("user",list);
//    }
//
//    @RequestMapping(value = "/user1", method = RequestMethod.GET)
//    public void findById(Model model) {
//        User user = userService.findById(1);
//        model.addAttribute("user", user);
//    }
//
//    @RequestMapping(value = "/user2", method = RequestMethod.GET)
//    public void findUserByUsernameAndPassword(Model model) {
//        List<User> users = userService.findByUsernameAndPassword("haha","12345678");
//        model.addAttribute("user", users);
//    }

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public void findEmployerByUsername(Model model, @CurrentUser Employess employess, @RequestHeader("account") String account) {
        System.out.print(employess.getPassword());
        List<CommonDTO> employees = systemService.findAll(account);
        model.addAttribute("emp", employees);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        acceptanceService.test();
    }


}
