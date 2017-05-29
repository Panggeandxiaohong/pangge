package online.pangge.exam.mapper;

import online.pangge.exam.domain.Classes;
import java.util.List;

public interface ClassesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Classes record);

    Classes selectByPrimaryKey(Long id);

    List<Classes> selectAll();

    int updateByPrimaryKey(Classes record);
}