package online.pangge.wechat.web.controller;


import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {

    @Autowired
    public ISubjectService subjectService;

    @RequestMapping("/exam.do")
    public String exam(Long id, Model model){
        Subject subject = subjectService.selectById(id);
        model.addAttribute("subject",subject);
        return "wechat/subjectView";
    }
}
