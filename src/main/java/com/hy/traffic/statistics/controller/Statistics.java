package com.hy.traffic.statistics.controller;

import com.hy.traffic.saftyEdu.service.impl.SaftyeduServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/statistics/statisticsinfo")
@CrossOrigin
public class Statistics {
    @Autowired
    SaftyeduServiceImpl saftyeduService;


    /*
    * @parm mq
    * 1到12月报名人数
    * */
    @RequestMapping("/year")
    @ResponseBody
    public Integer[] year(){
        return saftyeduService.year();
    }
}
