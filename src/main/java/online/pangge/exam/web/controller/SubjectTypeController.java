package online.pangge.exam.web.controller;

import online.pangge.exam.domain.SubjectType;
import online.pangge.exam.service.ISubjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jie34 on 2017/6/17.
 */
@Controller
public class SubjectTypeController {
    @Autowired
    private ISubjectTypeService subjectTypeService;
    @RequestMapping("/subject_type_list.do")
    @ResponseBody
    public List<SubjectType> getSubjectList(){
        return subjectTypeService.selectAll();
    }
}
