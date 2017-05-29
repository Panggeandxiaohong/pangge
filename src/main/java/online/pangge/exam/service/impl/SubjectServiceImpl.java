package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.mapper.SubjectMapper;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by jie34 on 2017/5/9.
 */
@Service
public class SubjectServiceImpl implements ISubjectService{
    @Autowired
    private SubjectMapper subjectMapper;
    @Override
    public void insert(Subject subject) {
        subjectMapper.insert(subject);
    }

    @Override
    public void delete(Long id) {
        subjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Subject subject) {
        subjectMapper.updateByPrimaryKey(subject);
    }

    @Override
    public Subject selectById(Long id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }

    @Override
    public PageResult<Subject> page(SubjectQueryObject qo) {
        Long count = subjectMapper.queryCount(qo);
        if(count <=0){
            return new PageResult(0, Collections.emptyList());
        }
        return new PageResult(count.intValue(), subjectMapper.queryList(qo));
    }
}
