package online.pangge.exam.mapper;


import online.pangge.exam.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询根元素
     * @return
     */
    List<Menu> queryForRoot();

    /**
     * 根据父元素id查询所有子元素,供queryForRoot()调用
     * @return
     */
    List<Menu> queryByPid();
}