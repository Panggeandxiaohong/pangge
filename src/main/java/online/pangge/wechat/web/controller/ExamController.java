package online.pangge.wechat.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {
    @RequestMapping("/exam.do")
    public String exam(String src,String type, Model model){
        System.out.println("src = "+src);
        System.out.println("type = "+type);
        model.addAttribute("src",src);
        model.addAttribute("type",type);
        return "wechat/subjectView";
    }
}
