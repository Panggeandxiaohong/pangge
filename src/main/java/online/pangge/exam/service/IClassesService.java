package online.pangge.exam.service;

import online.pangge.exam.domain.Classes;

import java.util.List;

/**
 * Created by jie34 on 2017/6/11.
 */
public interface IClassesService {
    public void insert(Classes classes);
    public void delete(Long id);
    public void update(Classes classes);
    public Classes selectById(Long id);
    public List<Classes> selectAll();
}
