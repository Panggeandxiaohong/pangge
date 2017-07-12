package online.pangge.exam.service.impl;

import online.pangge.exam.domain.SubjectType;
import online.pangge.exam.mapper.SubjectTypeMapper;
import online.pangge.exam.service.ISubjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jie34 on 2017/6/17.
 */
@Service
public class SubjectTypeServiceImpl implements ISubjectTypeService {
    @Autowired
    private SubjectTypeMapper subjectTypeDao;
    @Override
    public SubjectType selectById(Long id) {
        return subjectTypeDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SubjectType> selectAll() {
        return subjectTypeDao.selectAll();
    }
}
