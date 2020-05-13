package com.jy.service.menu.impl;

import com.jy.mapper.menu.MenuMapper;
import com.jy.model.menu.Menu;
import com.jy.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectIndexPageNavList(Menu menu) {
        List<Menu> list = menuMapper.selectIndexPageNavList(menu);
        if (null != list && 0 < list.size()) {
            //遍历一级节点（pid为0的节点）
            for (Menu m1 : list) {
                Menu m2 = new Menu();
                m2.setPid(m1.getId());
                //查询该一级节点的所有子节点
                List<Menu> clist = selectIndexPageNavList(m2);
                if (null != clist && 0 < clist.size()) {
                    //把子节点集合放到父节点的children属性中
                    m1.setChildren(clist);
                }
            }
        }
        return list;
    }

    @Override
    public Set<String> selectMenuListByUserID(Menu menu) {
        Set<String> permSet = menuMapper.selectMenuListByUserID(menu);
        return permSet;
    }
}
