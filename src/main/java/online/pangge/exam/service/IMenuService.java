package online.pangge.exam.service;


import online.pangge.exam.domain.Menu;

import java.util.List;

public interface IMenuService {
    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> queryForMenu();

}
