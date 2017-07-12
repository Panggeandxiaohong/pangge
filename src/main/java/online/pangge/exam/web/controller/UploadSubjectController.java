package online.pangge.exam.web.controller;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.IClassesService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.service.ISubjectTypeService;
import online.pangge.exam.util.AjaxResult;
import online.pangge.exam.util.ExamConst;
import online.pangge.exam.util.OSSUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by jie34 on 2017/4/30.
 */
@Controller
public class UploadSubjectController {
    private static Logger logger = Logger.getLogger(UploadSubjectController.class);
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private ISubjectTypeService subjectTypeService;
    @Autowired
    private IClassesService classesService;
    @Autowired
    private OSSUtil ossUtil;

    @RequestMapping("/subject.do")
    public String subject() {
        return "subject";
    }

    @RequestMapping("/subject_list.do")
    @ResponseBody
    public PageResult<Subject> subjectList(HttpServletRequest req) {
        SubjectQueryObject qo = new SubjectQueryObject();
        qo.setPage(Integer.valueOf(StringUtils.isEmpty(req.getParameter("page"))?"5":req.getParameter("page")));
        qo.setRows(Integer.valueOf(StringUtils.isEmpty(req.getParameter("rows"))?"5":req.getParameter("rows")));
        qo.setClasses_combobox(Long.valueOf(StringUtils.isEmpty(req.getParameter("classes_combobox"))?"-1":req.getParameter("classes_combobox")));
        qo.setSubject_type_combobox(Long.valueOf(StringUtils.isEmpty(req.getParameter("subject_type_combobox"))?"-1":req.getParameter("subject_type_combobox")));
        qo.setMedia_type_combobox(StringUtils.isEmpty(req.getParameter("media_type_combobox"))?null:req.getParameter("media_type_combobox"));
        qo.setProcess_status_combobox(StringUtils.isEmpty(req.getParameter("process_status_combobox"))?null:req.getParameter("process_status_combobox"));
        qo.setKeyword(req.getParameter("keyword"));
        PageResult<Subject> page = subjectService.page(qo);
        return page;
    }

    private String getFileType(MultipartFile myfiles){
        String fileNameRar = myfiles.getOriginalFilename();
        String[] fileTypesRar = fileNameRar.split("\\.");
        return  StringUtils.lowerCase(fileTypesRar[fileTypesRar.length - 1]);
    }

    private File getFile(MultipartFile myfiles){
        CommonsMultipartFile cf= (CommonsMultipartFile)myfiles;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        return fi.getStoreLocation();
    }
    @RequestMapping("/subject_upload.do")
    @ResponseBody
    public AjaxResult subjectUpload(MultipartFile subjects,MultipartFile more_myfiles,HttpServletRequest req) throws Exception {
        if(!ExamConst.subjectsFileType.contains(getFileType(subjects))||!ExamConst.subjectFilesType.contains(getFileType(more_myfiles))){
            return new AjaxResult(false, "操作失败！文件类型不正确！");
        }
        String fileName = subjects.getOriginalFilename();
        OutputStream outputStream = new FileOutputStream(fileName);
        InputStream inputStream = subjects.getInputStream();
        IOUtils.copy(inputStream, outputStream);
        subjectService.importSubject(new File(fileName),getFile(more_myfiles));
        return new AjaxResult(true, "操作成功！");
    }

    @RequestMapping("/subject_delete.do")
    @ResponseBody
    public AjaxResult deleteSubject(@RequestParam("ids[]") List<Long> params,String type) {
        AjaxResult result = null;
        String activeOrDelete = null;
        if(ExamConst.subject_process_status_delete.equals(type)){
            activeOrDelete=ExamConst.subject_process_status_delete;
        }else{
            activeOrDelete=ExamConst.subject_process_status_active;
        }
        int deleteCount = subjectService.delete(params,activeOrDelete);
        if(deleteCount==0){
            result = new AjaxResult(false, "操作失败，操作的条目为0！");
        }else{
            result = new AjaxResult(true, "操作成功！");
        }
        return result;
    }

    @RequestMapping("/subjectomer_save.do")
    @ResponseBody
    public AjaxResult saveSubject(MultipartFile myfiles, HttpServletRequest request){
        System.out.println("ininininin");
        AjaxResult result = null;
        Subject subject = new Subject();
        subject.setMediaType(request.getParameter("media_type"));
        subject.setSubjectType(subjectTypeService.selectById(Long.valueOf(request.getParameter("subject_type"))));
        if (ExamConst.wechat_subject_type_choice.equals(subject.getSubjectType().getId())) {
            subject.setAnswerA(request.getParameter("answerA"));
            subject.setAnswerB(request.getParameter("answerB"));
            subject.setAnswerC(request.getParameter("answerC"));
            subject.setAnswerD(request.getParameter("answerD"));
        }
        if(!ExamConst.wechat_material_type_text.equals(subject.getMediaType())){
            if (myfiles.isEmpty()) {
                return new AjaxResult(false, "保存失败，请上传附件！");
            }
            String fileName = myfiles.getOriginalFilename();
            String[] fileTypes = fileName.split("\\.");
            String fileType = StringUtils.lowerCase(fileTypes[fileTypes.length - 1]);
            CommonsMultipartFile cf= (CommonsMultipartFile)myfiles;
            DiskFileItem fi = (DiskFileItem)cf.getFileItem();
            File file = fi.getStoreLocation();
            if (ExamConst.wechat_material_type_image.equals(subject.getMediaType())) {
                if (!ExamConst.images.contains(fileType)) {
                    return new AjaxResult(false, "图片格式不正确！");
                }
            } else if (ExamConst.wechat_material_type_video.equals(subject.getMediaType())) {
                if (!ExamConst.videos.contains(fileType)) {
                    return new AjaxResult(false, "视频格式不正确！");
                }
                return new AjaxResult(false,"系统暂不支持视频格式~");
//                subject.setUrl(FileUtil.addMaterialEver("G:\\ideaworkspace\\wechat\\src\\main\\java\\online\\pangge\\IMG_6572.JPG",ExamConst.wechat_material_type_video, WeixinUtil.getAccessToken()));
            } else if (ExamConst.wechat_material_type_voice.equals(subject.getMediaType())) {
                if (!ExamConst.voices.contains(fileType)) {
                    return new AjaxResult(false, "音频格式不正确！");
                }
            }
            if(!ossUtil.isExist(myfiles.getOriginalFilename())){
                subject.setUrl(ossUtil.uploadObj(null,myfiles.getOriginalFilename(),file));
                logger.info("上传文件成功,key为："+myfiles.getOriginalFilename()+"，url为:"+subject.getUrl());
            }else{
                logger.info("上传文件失败，已经存在这个文件,key为："+myfiles.getOriginalFilename());
                return new AjaxResult(false,"该资源已经存在，请确认名字和题目！");
            }
        }
        subject.setQuestion(request.getParameter("question"));
        subject.setScore(Double.valueOf(request.getParameter("score")));
        subject.setClasses(classesService.selectById(Long.valueOf(request.getParameter("classes"))));
        subject.setAnswer(request.getParameter("answer"));
        subject.setExplain(request.getParameter("explain"));
        subject.setAddtime(new Date());
        if(subjectService.insert(subject)>0){

            logger.info("新增题目成功:"+subject.toString());
            result = new AjaxResult(true, "保存成功");
        }else{
            logger.info("新增题目失败，向数据库中写入题目时成功的条数为0 :"+subject.toString());
            result = new AjaxResult(false, "保存失败");
        }
        return result;
    }
}
