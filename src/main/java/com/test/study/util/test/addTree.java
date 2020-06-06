package com.test.study.util.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class addTree {

    private static List<MenuEO> buildMenuTree(List<MenuEO> menuList, String pid) {
        List<MenuEO> treeList = new ArrayList<>();
        menuList.forEach(menu -> {
            if (Objects.equals(pid, menu.getParentCode())) {
                menu.setChildMenus(buildMenuTree(menuList, menu.getCode()));
                treeList.add(menu);
            }
        });
        return treeList;
    }


    public static void main(String[] args) {
        // 原始的数据
//    List<MenuEO> rootMenu = menuDao.queryMenuList(null);
        List<MenuEO> rootMenu = new ArrayList<>();
        // 构建好的菜单树，第一层菜单的pid是null
        List<MenuEO> menuTree = buildMenuTree(rootMenu, null);
        System.out.println(menuTree);

    }


}
