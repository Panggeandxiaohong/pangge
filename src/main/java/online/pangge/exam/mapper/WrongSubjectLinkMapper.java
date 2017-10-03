package online.pangge.exam.mapper;

import online.pangge.exam.domain.WrongSubjectLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WrongSubjectLinkMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("subId") Long subId);

    int insert(WrongSubjectLink record);

    int insertWrongSubjectLinks(List<WrongSubjectLink> links);

    List<WrongSubjectLink> selectByPrimaryKey(@Param("userId") Long userId, @Param("subId") Long subId);

    List<WrongSubjectLink> selectAll();

}