package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Menu;
import online.pangge.exam.mapper.MenuMapper;
import online.pangge.exam.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuDAO;

    @Override
    public List<Menu> queryForMenu() {
        return menuDAO.queryForRoot();
    }
}
