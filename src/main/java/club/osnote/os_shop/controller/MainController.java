package club.osnote.os_shop.controller;

import club.osnote.os_shop.service.TestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    TestService testService;
    @Autowired
    Logger logger;

    @RequestMapping("/")
    public String index(){

        return "/index";
    }
}
