package online.pangge.exam.service;


import online.pangge.exam.domain.Role;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.RoleQueryObject;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色(高级查询)
     * @param qo 角色高级查询对象
     * @return
     */
    PageResult queryForPage(RoleQueryObject qo);


}
