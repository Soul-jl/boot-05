package com.jy.controller.index;

import com.jy.model.menu.Menu;
import com.jy.model.user.User;
import com.jy.service.menu.MenuService;
import com.jy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /*@RequestMapping("")
    String toIndex(ModelMap mm) {
        List<User> ulist = userService.selectUserList(new User());
        List<String> slist = new ArrayList<>();
        slist.add("第一条");
        slist.add("第二条");
        slist.add("第三条");
        slist.add("第四条");

        Map<String, String> map = new HashMap<>();
        map.put("roomNum", "1234");
        map.put("roomType", "标间");
        map.put("roomPrice", "88.9");

        mm.addAttribute("userName", "<font color='red'>张三</font>");
        mm.addAttribute("userAge", 23);
        mm.addAttribute("userIntr", "张地方撒旦公司的公司的对方电视广告");
        mm.addAttribute("ulist", ulist);
        mm.addAttribute("slist", slist);
        mm.addAttribute("map", map);
        return "index";
    }*/

    @RequestMapping("")
    String toIndex(ModelMap mm, Menu menu) {
        //查询首页左侧导航列表
        List<Menu> list = menuService.selectIndexPageNavList(menu);
        mm.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("noAuth")
    String toNoAuthPage() {
        return "noAuth";
    }

}
