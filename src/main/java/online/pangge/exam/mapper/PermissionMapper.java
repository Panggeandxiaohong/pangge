package online.pangge.exam.mapper;


import online.pangge.exam.domain.Permission;
import online.pangge.exam.query.PermissionQueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    Long queryForPageCount(PermissionQueryObject qo);

    List<Permission> queryForPage(PermissionQueryObject qo);

    /**
     * 查询员工id查询拥有权限
     * @param eid   员工id
     * @return  该员工拥有的权限表达式的集合
     */
    List<String> queryByEid(Long eid);

}