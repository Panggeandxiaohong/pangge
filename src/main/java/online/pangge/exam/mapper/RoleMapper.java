package online.pangge.exam.mapper;

import online.pangge.exam.domain.Role;
import online.pangge.exam.query.RoleQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Long queryForPageCount(RoleQueryObject qo);

    List<Role> queryForPage(RoleQueryObject qo);

    /**
     * 维护角色和权限的中间表关系
     * @param roleId    角色id
     * @param permissionId  权限id
     */
    void insertRelation(@Param("rid") Long roleId, @Param("pid") Long permissionId);

    /**
     * 根据角色id查询删除权限
     * @param rid   要删除的角色id
     */
    void deletePermissionByRid(Long rid);
}