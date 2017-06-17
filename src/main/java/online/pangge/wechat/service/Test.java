package online.pangge.wechat.service;

import com.google.gson.Gson;
import online.pangge.exam.domain.Classes;
import online.pangge.exam.domain.Student;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.IStudentService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mvc.xml")
public class Test {
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private RedisUtil redisUtil;

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
    public void jsontest(){
        Subject s = new Subject();
        s.setAnswerA("A");
        s.setAnswerB("B");
        s.setAnswerC("C");
        s.setAnswerD("D");
        s.setQuestion("question");
        Classes classes = new Classes();
        classes.setCreateDate(new Date());
        classes.setClassName("三年级二班");
        classes.setTeacher("李老师");
        s.setClasses(classes);
        Map<String,Classes> map1 = new HashMap<>();
        map1.put("sub",classes);
        Map<String,Classes> map2 = new HashMap<>();
        map2.put("sub",classes);
        Map<String,Classes> map3 = new HashMap<>();
        map3.put("sub",classes);
        Map<String,Classes> map4 = new HashMap<>();
        map4.put("sub",classes);
        List<Map<String,Classes>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        Gson g = new Gson();
        Map<String,Classes> mapp = g.fromJson(g.toJson(map4),Map.class);

        String sss = g.toJson(list);
        System.out.println("sss="+mapp.get("sub").getClassName());
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
