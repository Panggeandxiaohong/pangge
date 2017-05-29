package online.pangge.wechat.service;

import online.pangge.exam.domain.Student;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.IStudentService;
import online.pangge.exam.service.ISubjectService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class Test {
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IStudentService studentService;
    public static void main(String[] args) {
        String aa = "未经过jedis";
        System.out.println(aa);
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool pool = new JedisPool(config,"120.77.82.217",6379,1000,"XIAOlijun1314");
        Jedis js = pool.getResource();//new Jedis("120.77.82.217",6379);
       js.auth("XIAOlijun1314");
       js.select(1);
        js.set("a", "{id:1,name:fffffff}");
        aa= js.get("a");
        System.out.println("end");
        System.out.println(aa);
    }

    @org.junit.Test
    public void save(){
/*        Student stu = new Student();
        stu.setName("zhangsan");
        stu.setId(1L);
        Subject subject = new Subject();
        subject.setType("choice");
        subject.setQuestion("胖哥帅不帅");
        subject.setAnswerA("帅");
        subject.setAnswerB("A");
        Classes c = new Classes();
        c.setId(1L);
        c.setClassName("社会管理学");
        c.setClassroom("木有");
        c.setTeacher("张老师");
        c.setCreateDate(new Date());
        c.setLastUpdateDate(new Date());
        subject.setClasses(c);
        subject.setAddtime(new Date());
        Admin a = new Admin();
        a.setName("张老师");
        a.setId(1L);
        subject.setAdduser(a);
        subject.setAnswerC("非常帅");
        subject.setAnswerD("ABC都有");
        subject.setAnswer("D");
        subject.setExplain("帅是不需要理由的");
        subject.setUser(stu);
        subject.setUserAnswer("D");
        subjectService.insert(subject);*/
        SubjectQueryObject qo = new SubjectQueryObject();
        PageResult p = subjectService.page(qo);
        System.out.println(p.getTotal());
        List<Subject> list = p.getRows();
        for (Subject s:
             list) {
            System.out.println(s.toString());
        }
        Student ss = studentService.selectByPrimaryKey(211L);
            System.out.println(ss.toString());
//        studentService.insert(stu);
    }
}
