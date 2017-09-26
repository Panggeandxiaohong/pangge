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

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ExamController {

    private static Logger logger = Logger.getLogger(ExamController.class);

    @Autowired
    public ISubjectService subjectService;

    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping("/exam.do")
    public String exam(HttpServletRequest request, Model model) {
        String subjectString = "";
        try {
            System.out.println(subjectString);
            new String(request.getParameter("subjectString").getBytes("iso8859-1"),"UTF-8");
            System.out.println(subjectString);
        } catch (UnsupportedEncodingException e) {
            logger.error("decoder error",e);
        }
        Subject subject = new Gson().fromJson(subjectString, Subject.class);
        model.addAttribute("subject", subject);
        return "wechat/subjectView";
    }
}
