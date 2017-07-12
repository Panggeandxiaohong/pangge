package online.pangge.exam.web.controller;

import online.pangge.exam.domain.Classes;
import online.pangge.exam.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClassesController {
    @Autowired
    private IClassesService classesService;

    @RequestMapping("/classes_getclasses.do")
    @ResponseBody
    public List<Classes> getClasses(){
        return classesService.selectAll();
    }
}
