package online.pangge.exam.service;


import online.pangge.exam.domain.Permission;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.PermissionQueryObject;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    /**
     * 查询权限(高级查询)
     * @param qo
     * @return
     */
    PageResult queryForPage(PermissionQueryObject qo);

    /**
     * 根据员工的id查询拥有的权限
     * @param eid   员工id
     * @return
     */
    List<String> queryByEid(Long eid);

    /**
     * 加载所有权限
     */
    void reload();
}
