package online.pangge.wechat.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {
    @RequestMapping("/exam.do")
    public String exam(){

        return "wechat/subjectView";
    }
}
