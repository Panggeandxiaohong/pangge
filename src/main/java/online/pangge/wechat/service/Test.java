package online.pangge.wechat.service;

import com.google.gson.Gson;
import online.pangge.exam.domain.Classes;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.IStudentService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.RedisUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
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
        List<String> aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
        aa.add("c");
        aa.add("d");
        aa.add("e");
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool pool = new JedisPool(config, "39.108.2.41", 6379, 1000, "XIAOlijun1314");
        Jedis js = pool.getResource();//new Jedis("120.77.82.217",6379);
        js.auth("XIAOlijun1314");
        js.select(1);
//        js.set("a", SerializeUtil.serialize(aa).toString());
//        String a = js.get("a");
//        Object aaa = SerializeUtil.unSerialize(js.get("a").getBytes());
        js.rpush("sss","a");
        js.rpush("sss","b");
        js.rpush("sss","c");
        js.rpush("sss","d");
        js.rpush("sss","e");
        for(int i= 0;i<10;i++){
            System.out.println("i="+i+",has key = "+js.exists("sss"));
           String s =  js.rpop("sss");
            System.out.println("value = "+s);
        }
        System.out.println(js.get("a")+"end");
        System.out.println(aa);
    }

    @org.junit.Test
    public void jsontest() {
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
        Map<String, Classes> map1 = new HashMap<>();
        map1.put("sub", classes);
        Map<String, Classes> map2 = new HashMap<>();
        map2.put("sub", classes);
        Map<String, Classes> map3 = new HashMap<>();
        map3.put("sub", classes);
        Map<String, Classes> map4 = new HashMap<>();
        map4.put("sub", classes);
        List<Map<String, Classes>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        Gson g = new Gson();
        Map<String, Classes> mapp = g.fromJson(g.toJson(map4), Map.class);

        String sss = g.toJson(list);
        System.out.println("sss=" + mapp.get("sub").getClassName());
    }

    @org.junit.Test
    public void save() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"old", "0_start", "0_end"});
        list.add(new String[]{"old", "1_start", "1_end"});
        list.add(new String[]{"old", "2_start", "2_end"});
        list.add(new String[]{"new", "3_start", "3_end"});
        list.add(new String[]{"new", "4_start", "4_end"});
        list.add(new String[]{"old", "5_start", "5_end"});
        list.add(new String[]{"old", "6_start", "6_end"});
        list.add(new String[]{"old", "7_start", "7_end"});
        list.add(new String[]{"new", "8_start", "8_end"});
        list.add(new String[]{"old", "9_start", "9_end"});
        list.add(new String[]{"old", "10_start", "10_end"});
        list.add(new String[]{"old", "11_start", "11_end"});
        list.add(new String[]{"new", "12_start", "12_end"});
        list.add(new String[]{"old", "13_start", "13_end"});
        list.add(new String[]{"old", "14_start", "14_end"});
        list.add(new String[]{"new", "15_start", "15_end"});
        list.add(new String[]{"old", "16_start", "16_end"});
        list.add(new String[]{"old", "17_start", "17_end"});
        list.add(new String[]{"old", "18_start", "18_end"});
        list.add(new String[]{"new", "19_start", "19_end"});
        String oldString = "";
        String newString = "";
        String totalOldString = "old===";
        String totalnewString = "new===";
        for (int i = 0; i < list.size(); i++) {
            if ("old".equals(list.get(i)[0])) {
                if ("".equals(oldString)) {
                    oldString = list.get(i)[1];
                }
                if (i - 1 >= 0) {
                    if (i == (list.size() - 1)) {
                        if ("new".equals(list.get(i)[0])) {
                            if("old".equals(list.get(i-1)[0])){
                                oldString += "-" + list.get(i-1)[2] + ",";
                                totalOldString += oldString;
                                oldString = "";
                            }
                            newString += "-" + list.get(i)[2] + ",";
                            totalnewString += newString;
                            newString = "";
                        } else if ("old".equals(list.get(i)[0])) {
                            if("new".equals(list.get(i-1)[0])){
                                newString += "-" + list.get(i-1)[2] + ",";
                                totalnewString += newString;
                                newString = "";
                            }
                            oldString += "-" + list.get(i)[2] + ",";
                            totalOldString += oldString;
                            oldString = "";
                        }
                    } else {
                        if ("new".equals(list.get(i - 1)[0])) {
                            newString += "-" + list.get(i - 1)[2] + ",";
                            totalnewString += newString;
                            newString = "";
                        }
                    }
                } else if ((list.size() - 1) == i) {
                    oldString += "-" + list.get(i)[2];
                    totalOldString += oldString;
                }
            } else {
                if ("".equals(newString)) {
                    newString = list.get(i)[1];
                }
                if (i - 1 >= 0) {
                    if (i == (list.size() - 1)) {
                        if ("old".equals(list.get(i)[0])) {
                            if("new".equals(list.get(i-1)[0])){
                                newString += "-" + list.get(i-1)[2] + ",";
                                totalnewString += newString;
                                newString = "";
                            }
                            oldString += "-" + list.get(i)[2] + ",";
                            totalOldString += oldString;
                            oldString = "";
                        }else if("new".equals(list.get(i)[0])) {
                            if("old".equals(list.get(i-1)[0])){
                                oldString += "-" + list.get(i-1)[2] + ",";
                                totalOldString += oldString;
                                oldString = "";
                            }
                            newString += "-" + list.get(i)[2] + ",";
                            totalnewString += newString;
                            newString = "";
                        }
                    } else {
                        if ("old".equals(list.get(i - 1)[0])) {
                            oldString += "-" + list.get(i - 1)[2] + ",";
                            totalOldString += oldString;
                            oldString = "";
                        }
                    }
                } else if ((list.size() - 1) == i) {
                    newString += "-" + list.get(i)[2];
                    totalnewString += newString;
                }
            }
        }
        System.out.println(totalOldString);
        System.out.println(totalnewString);
    }

    @org.junit.Test
    public void testUnZip() {
        String srcPath = "G:\\mysql-5.7.11-winx64.zip";
        String descPath = "G:\\test";
        try {
            File descFile = new File(descPath);
            File srcFile = new File(srcPath);
            if (!srcFile.exists() && (srcFile.length() <= 0)) {
                throw new RuntimeException("the file is not exists");
            }
            ZipFile zipFile = new ZipFile(srcFile, "gbk");
            String strPath, gbkPath, strTemp;
            strPath = descPath;
            Enumeration<ZipEntry> enumeration = zipFile.getEntries();
            while (enumeration.hasMoreElements()) {
                ZipEntry zipEnt = enumeration.nextElement();
                gbkPath = zipEnt.getName();
                strTemp = strPath + File.separator + gbkPath;
                if (zipEnt.isDirectory()) {
                    File dir = new File(strTemp);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                } else {
                    InputStream in = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
                    String strSubdir = gbkPath;
                    for (int i = 0; i < strSubdir.length(); i++) {
                        if (strSubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = strPath + File.separator + strSubdir.substring(0, i);
                            File subDir = new File(temp);
                            if (!subDir.exists()) {
                                subDir.mkdir();
                            }
                        }
                    }
                    FileOutputStream out = new FileOutputStream(strTemp);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
                    int len;
                    byte[] buff = new byte[2014];
                    while ((len = bufferedInputStream.read(buff)) != -1) {
                        bufferedOutputStream.write(buff, 0, len);
                    }
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                }
            }
            zipFile.close();
            System.out.println(srcFile.delete());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
