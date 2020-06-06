package com.test.study.util.test;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wlm
 */

@Data
public class MenuEO  implements Serializable {

    private static final long serialVersionUID = -9218380392335612600L;


    private Long id;

    //菜单编码
    private String code;

    //菜单名
    private String name;

    //上级菜单编码
    private String parentCode;


    private List<MenuEO> childMenus;

}

