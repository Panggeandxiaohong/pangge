package online.pangge.exam.service;

import online.pangge.exam.domain.SubjectType;

import java.util.List;

/**
 * Created by jie34 on 2017/6/17.
 */
public interface ISubjectTypeService {
    public SubjectType selectById(Long id);
    public List<SubjectType> selectAll();
}
