package online.pangge.exam.mapper;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.query.SubjectQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {
    int deleteByPrimaryKey(@Param("list") List<Long> id, @Param("process_status") String process_status);

    int insert(Subject record);

    Subject selectByPrimaryKey(Long id);

    List<Subject> selectAll();

    int updateByPrimaryKey(Subject record);

    long queryCount(SubjectQueryObject qo);

    List<Subject> queryList(SubjectQueryObject qo);
}