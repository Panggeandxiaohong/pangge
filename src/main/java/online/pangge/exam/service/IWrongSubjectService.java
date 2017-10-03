package online.pangge.exam.service;

import online.pangge.exam.domain.WrongSubjectLink;

import java.util.List;

/**
 * Created by jie34 on 2017/10/3.
 */
public interface IWrongSubjectService {
    int deleteByPrimaryKey(Long userId,Long subId);

    int insert(WrongSubjectLink record);

    int insertWrongSubjectLinks(List<WrongSubjectLink> record);

    List<WrongSubjectLink> selectByPrimaryKey(Long userId,Long subId);

    List<WrongSubjectLink> selectAll();
}
