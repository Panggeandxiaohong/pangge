package online.pangge.exam.web.controller;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jie34 on 2017/4/30.
 */
@Controller
public class UploadSubjectController {
    @Autowired
    private ISubjectService subjectService;
    @RequestMapping("/subject.do")
    public String subject(){
        return "subject";
    }
    @RequestMapping("/subject_list.do")
    @ResponseBody
    public PageResult<Subject> subjectList(){
        PageResult<Subject> page =subjectService.page(new SubjectQueryObject());
//        List<Subject> list = page.getRows();
//        for (Subject s:
//             list) {
//            System.out.println(s.toString());
//        }
//        System.out.println();
        return page;
    }

    @RequestMapping("/subjectomer_save.do")
    @ResponseBody
    public AjaxResult saveSubject(HttpServletRequest request){
        AjaxResult result = null;
        System.out.println("in...");
        System.out.println(request.getParameter("question"));
        System.out.println(request.getParameter("type"));
        System.out.println(request.getParameter("score"));
        System.out.println(request.getParameter("classes"));
        System.out.println(request.getParameter("answerA"));
        System.out.println(request.getParameter("answerB"));
        System.out.println(request.getParameter("answerD"));
//        System.out.println(file.getName());
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
        result = new AjaxResult(true, "保存成功");
        return result;
    }
}
