package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Permission;
import online.pangge.exam.domain.Role;
import online.pangge.exam.mapper.RoleMapper;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.RoleQueryObject;
import online.pangge.exam.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        roleDAO.deletePermissionByRid(id);
        return roleDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        int affectCount = roleDAO.insert(record);
        for (Permission p : record.getPermissions()) {
            roleDAO.insertRelation(record.getId(),p.getId());
        }
        return affectCount;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleDAO.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        int effectCount = roleDAO.updateByPrimaryKey(record);
        //删除以前的关系
        roleDAO.deletePermissionByRid(record.getId());
        //建立新的关系
        for (Permission permission : record.getPermissions()) {
            roleDAO.insertRelation(record.getId(),permission.getId());
        }
        return effectCount;
    }


    @Override
    public PageResult queryForPage(RoleQueryObject qo) {
        //查询总的记录数
        Long count = roleDAO.queryForPageCount(qo);
        if (count==0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Role> result = roleDAO.queryForPage(qo);
        return new PageResult(count.intValue(),result);
    }

}
