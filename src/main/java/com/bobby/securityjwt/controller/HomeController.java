package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @author: Bobby
 * @date: 10/13/2023
 **/
@RestController
public class HomeController {

    @GetMapping("/read")
    public AjaxResult read() {
        return AjaxResult.success("emm.. what do you want to read?");
    }


}
