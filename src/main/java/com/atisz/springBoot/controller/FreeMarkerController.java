package com.atisz.springBoot.controller;

import com.atisz.springBoot.entity.UserEntity;
import com.atisz.springBoot.mapper.UserMapper_xml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class FreeMarkerController {

    @Autowired
    private UserMapper_xml userMapper_xml;

    @RequestMapping("/login")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");

        List<String> userList = new ArrayList<>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");

        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping("/mybatis")
    public ModelAndView getAllUser(ModelAndView modelAndView) {
        modelAndView.setViewName("userList");

        List<UserEntity> users = userMapper_xml.getAll();
        for (UserEntity user : users) {
            System.out.println(user.toString());
        }

        modelAndView.addObject("users", users);

        //两种方式都可行
//        ModelMap modelMap = new ModelMap();
//        modelMap.addAttribute("users", users);
//        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }
}
