package online.pangge.exam.util;

import online.pangge.exam.domain.Classes;
import online.pangge.exam.domain.Student;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.domain.SubjectType;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis cache 工具类
 *
 */
public final class RedisUtil {
    private Logger logger = Logger.getLogger(RedisUtil.class);
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            logger.info("remove keys = "+key);
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            logger.info("remove pattern = "+pattern);
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            logger.info("remove sinle key = "+key);
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            logger.info("set key - value,key = "+key+",value="+value.getClass());
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            logger.info("set key - value,key = "+key+",value="+value.getClass()+",expire time = "+expireTime);
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

        public boolean setSubject(final String key, List<Subject> subject) {
            boolean result = false;
            try {
                HashOperations<Serializable, String, Object> hash = redisTemplate.opsForHash();
                ListOperations<Serializable, Object> list = redisTemplate.opsForList();
                for (int i = 0; i < subject.size(); i++) {
                    System.out.println("question = "+subject.get(i).getQuestion().getClass());
                    System.out.println("subject = "+subject.get(i).getClass());
                    System.out.println("list = "+subject.getClass());
                    System.out.println("key = "+key + i);
                    hash.put(key + i, "question", subject.get(i).getQuestion());
                    hash.put(key + i, "type", subject.get(i).getSubjectType());
                    hash.put(key + i, "score", subject.get(i).getScore());
                    hash.put(key + i, "classes", subject.get(i).getClasses());
                    hash.put(key + i, "A", subject.get(i).getAnswerA());
                    hash.put(key + i, "B", subject.get(i).getAnswerB());
                    hash.put(key + i, "C", subject.get(i).getAnswerC());
                    hash.put(key + i, "D", subject.get(i).getAnswerD());
                    hash.put(key + i, "answer", subject.get(i).getAnswer());
                    hash.put(key + i, "userAnswer", subject.get(i).getUserAnswer());
                    hash.put(key + i, "user", subject.get(i).getUser());
                    hash.put(key + i, "explain", subject.get(i).getExplain());
                    hash.put(key + i, "url", subject.get(i).getUrl());
                    list.rightPush(key, key + i);
                    logger.info("insert subject = "+subject);
                    result = true;
                }
            } catch (Exception e) {
                logger.error("insert subject error : "+e.getMessage());
                e.printStackTrace();
            }
            return result;
        }

        public Subject getSubject(final String key){
            HashOperations<Serializable, String, Object> hash = redisTemplate.opsForHash();
            ListOperations<Serializable, Object> list = redisTemplate.opsForList();
            if(get("subjectNumber")==null){
                set("subjectNumber", 1);
            }
            System.out.println("subject number = "+get("subjectNumber"));
            Integer number = Integer.valueOf(get("subjectNumber").toString());
            String kkk = key+number;
            System.out.println(kkk);
            String subject = list.leftPop(kkk).toString();
            System.out.println("subject===="+subject);
            set("subjectNumber",number + 1);
            String quest = (String) hash.get(subject,"question");
            Double score = (Double) hash.get(subject,"score");
            Classes classes = (Classes) hash.get(subject,"classes");
            String A = (String) hash.get(subject,"A");
            String B = (String) hash.get(subject,"B");
            String C = (String) hash.get(subject,"C");
            String D = (String) hash.get(subject,"D");
            String answer = (String) hash.get(subject,"answer");
            String userAnswer = (String) hash.get(subject,"userAnswer");
            Student user = (Student) hash.get(subject,"user");
            String explain = (String) hash.get(subject,"explain");
            String url = (String) hash.get(subject,"url");
            Subject s = new Subject();
            s.setQuestion(quest);
            SubjectType subjectType = (SubjectType) hash.get(subject,"subjectType");
            s.setSubjectType(subjectType);
            s.setScore(score);
            s.setClasses(classes);
            s.setAnswerA(A);
            s.setAnswerB(B);
            s.setAnswerC(C);
            s.setAnswerD(D);
            s.setAnswer(answer);
            s.setUserAnswer(userAnswer);
            s.setUser(user);
            s.setExplain(explain);
            s.setUrl(url);
            return s;
        }
    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
