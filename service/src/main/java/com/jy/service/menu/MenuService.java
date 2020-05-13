package com.jy.service.menu;

import com.jy.model.menu.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {
    List<Menu> selectIndexPageNavList(Menu menu);

    Set<String> selectMenuListByUserID(Menu menu);

}
