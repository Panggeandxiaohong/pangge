package online.pangge.exam.service;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by jie34 on 2017/5/9.
 */
public interface ISubjectService {
    public int insert(Subject subject);
    public int delete(List<Long> id,String process_status);
    public int update(Subject subject);
    public Subject selectById(Long id);
    public List<Subject> selectAll();
    public PageResult<Subject> page(SubjectQueryObject qo);

    void importSubject(File subjects, File files) throws FileNotFoundException, Exception;
}
