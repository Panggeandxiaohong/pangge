import online.pangge.exam.domain.Permission;
import online.pangge.exam.service.IMenuService;
import online.pangge.exam.service.IPermissionService;
import online.pangge.exam.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class test {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IPermissionService permissionService;
    @Test
    public void testLog(){
//      Student log = studentService.selectByPrimaryKey("204");
//        List<Menu> m =menuService.queryForMenu();
        Permission p = new Permission();
        p.setName("传说中的离职");
        p.setResource("asdfasdfasdf");
        int i = permissionService.insert(p);
        System.out.println("影响的行数="+i);
//        for (Permission mm:m) {
//            System.out.println("test = = = = = ="+mm.getName());
//        }
    }
}