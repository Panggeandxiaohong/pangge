package online.pangge.wechat.web.controller;


import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.ExamConst;
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
    public String exam(String fromUserName, Model model) {
        Subject subject = redisUtil.getSubject(fromUserName + ExamConst.exam_type_exercise);
        redisUtil.setSubject(fromUserName + ExamConst.exam_type_temp,subject);
        redisUtil.set(fromUserName + "subjectNumber", Integer.valueOf(redisUtil.get(fromUserName+"subjectNumber").toString())+1);
        model.addAttribute("subject", subject);
        return "wechat/subjectView";
    }
}
