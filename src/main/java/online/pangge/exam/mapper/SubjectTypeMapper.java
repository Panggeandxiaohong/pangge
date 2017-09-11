package online.pangge.exam.mapper;

import online.pangge.exam.domain.SubjectType;

import java.util.List;

public interface SubjectTypeMapper {

    SubjectType selectByPrimaryKey(Long id);

    List<SubjectType> selectAll();
}