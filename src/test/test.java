import online.pangge.exam.domain.Permission;
import online.pangge.exam.service.IMenuService;
import online.pangge.exam.service.IPermissionService;
import online.pangge.exam.service.IStudentService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.ExamConst;
import online.pangge.exam.util.OSSUtil;
import online.pangge.exam.util.UploadFIleUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class test {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private OSSUtil ossUtil;
    @Autowired
    private ISubjectService subjectService;
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

    @Test
    public void testUnzip() throws ParseException {
//        FileUploadUtil.unzipFile("G:\\ideaworkspace\\wechat\\src\\main\\webapp\\upload\\aliyun_java_sdk_20170222.zip","D:\\unzipSubject");
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> s = new HashMap<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date addTime = fmt.parse("2011-11-11");
        s.put("addtime", addTime);
        map.put("process_status", ExamConst.subject_process_status_active);
        subjectService.update(s,map);
    }
    @Test
    public void testupload() throws ParseException {
        File f = new File("G:\\ideaworkspace\\wechat\\src\\main\\webapp\\upload");
        System.out.println(Runtime.getRuntime().availableProcessors());
        try {
            UploadFIleUtil.uploadFilesToSubject(f,ossUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}