package com.lk.basic.centerykt.controller;

/**
 * Created by Administrator on 2017/3/1.
 */

import com.lk.basic.centerykt.domain.BasicCenterYkt;
import com.lk.basic.centerykt.service.BasicCenterYktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/basic")
public class BasicCenterYktController {

    @Autowired
    private BasicCenterYktService basicCenterYktService;

    @RequestMapping(value = "/centerYkt")
    String queryData(Model model) {
        model.addAttribute("centerYkts", basicCenterYktService.findAll());
        return "/basic/centerykt/list";
    }

    @RequestMapping(value = "/centerYkt!create")
    public String create(){
        return "basic/centerykt/create";
    }

    @RequestMapping(value = "/centerYkt!save", method = RequestMethod.POST)
    public String save(BasicCenterYkt BasicCenterYkt){
        basicCenterYktService.save(BasicCenterYkt);
        return "redirect:centerYkt";
    }

    @RequestMapping(value = "/centerYkt!edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("basicCenterYkt",basicCenterYktService.findById(id));
        return "basic/centerykt/edit";
    }


    @RequestMapping(value = "/centerYkt!update",method = RequestMethod.POST)
    public String update(BasicCenterYkt basicCenterYkt){
        basicCenterYktService.save(basicCenterYkt);
        return "redirect:centerYkt";
    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return " hello world";
    }
}
