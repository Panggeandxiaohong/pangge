package online.pangge.exam.web.controller;

import online.pangge.exam.domain.Menu;
import online.pangge.exam.util.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jie34 on 2017/5/7.
 */
@Controller
public class IndexController {
    @RequestMapping("/index.do")
    public String main() {
        return "index";
    }

    @RequestMapping("/queryForMenu.do")
    @ResponseBody
    public List<Menu> queryForMenu() {
        List<Menu> result = null;
        //result =menuService.queryForMenu();
        result = (List<Menu>) UserContext.getLocal().getSession().getAttribute(UserContext.MENUINSESSION);
        return result;
    }
}
