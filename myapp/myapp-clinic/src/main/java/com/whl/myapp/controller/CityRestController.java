package com.whl.myapp.controller;



import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public String findOneCity() {
        return "hello clinic!";
    }
}
