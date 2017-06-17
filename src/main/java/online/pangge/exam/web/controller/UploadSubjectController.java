package online.pangge.exam.web.controller;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.IClassesService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jie34 on 2017/4/30.
 */
@Controller
public class UploadSubjectController {
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IClassesService classesService;
    @RequestMapping("/subject.do")
    public String subject(){
        return "subject";
    }
    @RequestMapping("/subject_list.do")
    @ResponseBody
    public PageResult<Subject> subjectList(HttpServletRequest req){
        PageResult<Subject> page =subjectService.page(new SubjectQueryObject());
        return page;
    }

    @RequestMapping("/uploadFile.do")
    @ResponseBody
    public void uploadFile(MultipartFile file){
        System.out.println("in upload method...");
        System.out.println(file.getName());
    }

    @RequestMapping("/subjectomer_save.do")
    @ResponseBody
    public AjaxResult saveSubject(MultipartFile myfiles, HttpServletRequest request){
        System.out.println("ininininin");
        AjaxResult result = null;
        Subject subject = new Subject();
        subject.setQuestion(request.getParameter("question"));
        subject.setType(request.getParameter("type"));
        subject.setScore(Double.valueOf(request.getParameter("score")));
        subject.setClasses(classesService.selectById(Long.valueOf(request.getParameter("classes"))));
        subject.setAnswerA(request.getParameter("answerA"));
        subject.setAnswerB(request.getParameter("answerB"));
        subject.setAnswerC(request.getParameter("answerC"));
        subject.setAnswerD(request.getParameter("answerD"));
        subject.setAnswer(request.getParameter("answer"));
        subject.setExplain(request.getParameter("explain"));
        System.out.println(myfiles.isEmpty());
        System.out.println(myfiles.getName());
        System.out.println(myfiles.getOriginalFilename());
        System.out.println(myfiles.getSize());
        result = new AjaxResult(true, "保存成功");
        return result;
    }
}
