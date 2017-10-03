package online.pangge.exam.mapper;

import online.pangge.exam.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    @Select("select * from student where id=#{id}")
    public Student selectById(@Param("id")Long id);

    @Select("select * from student where stunum=#{stunum}")
    public List<Student> selectByStunum(@Param("stunum")Long stunum);

    @Select("select * from student where wechatname=#{wechatname}")
    public List<Student> selectByWechatName(@Param("wechatname")String wechatname);
}