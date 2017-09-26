package online.pangge.wechat.web.controller;


import com.google.gson.Gson;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class ExamController {

    private static Logger logger = Logger.getLogger(ExamController.class);

    @Autowired
    public ISubjectService subjectService;

    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping("/exam.do")
    public String exam(String subjectString, Model model) {
        try {
            System.out.println(subjectString);
            subjectString = URLDecoder.decode(subjectString,"utf-8");
            System.out.println(subjectString);
        } catch (UnsupportedEncodingException e) {
            logger.error("decoder error",e);
        }
        Subject subject = new Gson().fromJson(subjectString, Subject.class);
        model.addAttribute("subject", subject);
        return "wechat/subjectView";
    }
}
