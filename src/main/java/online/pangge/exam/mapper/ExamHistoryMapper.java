package online.pangge.exam.mapper;

import online.pangge.exam.domain.ExamHistory;

import java.util.List;

public interface ExamHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamHistory record);

    ExamHistory selectByPrimaryKey(Long id);

    List<ExamHistory> selectAll();

    int updateByPrimaryKey(ExamHistory record);
}