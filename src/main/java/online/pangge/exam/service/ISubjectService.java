package online.pangge.exam.service;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;

import java.util.List;

/**
 * Created by jie34 on 2017/5/9.
 */
public interface ISubjectService {
    public void insert(Subject subject);
    public void delete(Long id);
    public void update(Subject subject);
    public Subject selectById(Long id);
    public List<Subject> selectAll();
    public PageResult<Subject> page(SubjectQueryObject qo);
}
