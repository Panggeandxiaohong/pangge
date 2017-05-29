package online.pangge.exam.mapper;

import online.pangge.exam.domain.Courses;
import java.util.List;

public interface CoursesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Courses record);

    Courses selectByPrimaryKey(Long id);

    List<Courses> selectAll();

    int updateByPrimaryKey(Courses record);
}