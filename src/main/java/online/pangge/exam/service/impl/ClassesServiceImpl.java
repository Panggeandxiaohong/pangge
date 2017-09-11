package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Classes;
import online.pangge.exam.mapper.ClassesMapper;
import online.pangge.exam.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jie34 on 2017/6/11.
 */
@Service
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public void insert(Classes classes) {
        classesMapper.insert(classes);
    }

    @Override
    public void delete(Long id) {
        classesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Classes classes) {
        classesMapper.updateByPrimaryKey(classes);
    }

    @Override
    public Classes selectById(Long id) {
        return classesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Classes> selectAll() {
        return classesMapper.selectAll();
    }
}
