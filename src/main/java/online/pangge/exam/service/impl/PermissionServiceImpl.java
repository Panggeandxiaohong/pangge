package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Permission;
import online.pangge.exam.mapper.PermissionMapper;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.PermissionQueryObject;
import online.pangge.exam.service.IPermissionService;
import online.pangge.exam.util.CommonUtil;
import online.pangge.exam.util.RequiredPermission;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware {
    @Autowired
    private PermissionMapper permissionDAO;
    private ApplicationContext ctx;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        int affectCount = permissionDAO.insert(record);
        return affectCount;
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionDAO.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionDAO.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryForPage(PermissionQueryObject qo) {
        //查询总的记录数
        Long count = permissionDAO.queryForPageCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Permission> result = permissionDAO.queryForPage(qo);
        return new PageResult(count.intValue(), result);
    }

    @Override
    public List<String> queryByEid(Long eid) {
        return permissionDAO.queryByEid(eid);
    }

    @Override
    public void reload() {
        //查询出数据库所有的权限表达式
        List<String> allPermissions = CommonUtil.allPermissions;
        Set<String> permissionExpermissions = new HashSet<>();//存储所有的权限表达式
        for (String permission : allPermissions) {
            permissionExpermissions.add(permission);
        }

        //1.获取controller类中所有子类中的所有方法
        Map<String, Object> controllerMap = ctx.getBeansWithAnnotation(org.springframework.stereotype.Controller.class);
        for (Object controller : controllerMap.values()) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String exp = method.getDeclaringClass().getName() + ":" + method.getName();
                //判断当前方法是否贴了RequiredPermission注解
                if(method.isAnnotationPresent(RequiredPermission.class)){
                    if (!permissionExpermissions.contains(exp)){
                        //该方法权限表达式不在数据库中
                        RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
                        Permission p = new Permission();
                        //设置权限名称
                        p.setName(rp.value());
                        //设置权限表达式
                        p.setResource(exp);
                        permissionDAO.insert(p);
                        //重新设置所有权限
                        List<Permission> Permissions = permissionDAO.selectAll();
                        for (Permission permission : Permissions) {
                            CommonUtil.allPermissions.add(permission.getResource());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
