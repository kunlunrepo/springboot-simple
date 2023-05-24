package com.msb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description :
 * @author kunlunrepo
 * date :  2023-05-23 19:28
 */
@Controller
public class InterceptorController {

    private static final Logger log = LoggerFactory.getLogger(InterceptorController.class);

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        log.info("【控制器-SimpleController】login：--- {} ", request);
        return "登录";
    }

}
