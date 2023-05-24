package com.msb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description :
 * @author kunlunrepo
 * date :  2023-05-23 19:28
 */
@Controller
public class AspectController {

    private static final Logger log = LoggerFactory.getLogger(AspectController.class);

    @RequestMapping(value = "/log", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String log(HttpServletRequest request) {
        log.info("【控制器-AspectController】log：--- {} ", request);
        return "登录";
    }

}
