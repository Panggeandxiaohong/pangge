package online.pangge.exam.service;

import online.pangge.exam.domain.Student;

import java.util.List;

/**
 * Created by jie34 on 2017/4/9.
 */
public interface IStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    List<Student> selectByWechatName(String wechatname);

    List<Student> selectByStunum(Long stunum);
}
