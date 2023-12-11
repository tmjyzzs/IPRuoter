package com.thgykj.ruoter.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Description index
 * DATA 2023-12-11
 *
 * @Author ttt
 */

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("test","hello");
        model.addAllAttributes(objectObjectHashMap);

        return "index";
    }
}
