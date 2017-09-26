package online.pangge.wechat.web.controller;


import com.google.gson.Gson;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {

    @Autowired
    public ISubjectService subjectService;

    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping("/exam.do")
    public String exam(String subjectString, Model model) {
        Subject subject = new Gson().fromJson(subjectString, Subject.class);
        model.addAttribute("subject", subject);
        return "wechat/subjectView";
    }
}
