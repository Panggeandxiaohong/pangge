package online.pangge.exam.service.impl;

import online.pangge.exam.domain.WrongSubjectLink;
import online.pangge.exam.mapper.WrongSubjectLinkMapper;
import online.pangge.exam.service.IWrongSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jie34 on 2017/10/3.
 */
@Service
public class WrongSubjetcLinkServiceImpl implements IWrongSubjectService {
    @Autowired
    private WrongSubjectLinkMapper wrongSubjectLinkMapper;

    @Override
    public int deleteByPrimaryKey(Long userId, Long subId) {
        return wrongSubjectLinkMapper.deleteByPrimaryKey(userId, subId);
    }

    @Override
    public int insert(WrongSubjectLink record) {
        return wrongSubjectLinkMapper.insert(record);
    }

    @Override
    public int insertWrongSubjectLinks(List<WrongSubjectLink> record) {
        return wrongSubjectLinkMapper.insertWrongSubjectLinks(record);
    }

    @Override
    public List<WrongSubjectLink> selectByPrimaryKey(Long userId, Long subId) {
        return wrongSubjectLinkMapper.selectByPrimaryKey(userId, subId);
    }

    @Override
    public List<WrongSubjectLink> selectAll() {
        return wrongSubjectLinkMapper.selectAll();
    }
}
