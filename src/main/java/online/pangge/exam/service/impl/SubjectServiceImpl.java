package online.pangge.exam.service.impl;

import online.pangge.exam.domain.Admin;
import online.pangge.exam.domain.Subject;
import online.pangge.exam.mapper.ClassesMapper;
import online.pangge.exam.mapper.SubjectMapper;
import online.pangge.exam.mapper.SubjectTypeMapper;
import online.pangge.exam.page.PageResult;
import online.pangge.exam.query.SubjectQueryObject;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.ExamConst;
import online.pangge.exam.util.FileUploadUtil;
import online.pangge.wechat.util2.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by jie34 on 2017/5/9.
 */
@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectTypeMapper subjectTypeMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public int insert(Subject subject) {
        return subjectMapper.insert(subject);
    }

    @Override
    public int delete(List<Long> id, String process_status) {
        return subjectMapper.deleteByPrimaryKey(id, process_status);
    }

    @Override
    public int update(Map subject, Map<String,Object> params) {
        return subjectMapper.updateByPrimaryKey(subject,params);
    }

    @Override
    public Subject selectById(Long id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }

    @Override
    public PageResult<Subject> page(SubjectQueryObject qo) {
        Long count = subjectMapper.queryCount(qo);
        if (count <= 0) {
            return new PageResult(0, Collections.emptyList());
        }
        return new PageResult(count.intValue(), subjectMapper.queryList(qo));
    }

    @Override
    public void importSubject(MultipartFile subjects, MultipartFile files) throws IOException {
        List<Subject> subjectDatas = null;
        subjectDatas = readExcel(subjects, FileUtil.getPostfix(subjects.getOriginalFilename()));
        subjectMapper.insertSubjects(subjectDatas);
        FileUploadUtil.fileUpload(files);
    }

    private List<Subject> readExcel(MultipartFile file, String postfix) throws IOException {
        if (!ExamConst.EMPTY.equals(postfix)) {
            if (ExamConst.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                return readXls(file);
            } else if (ExamConst.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                return readXlsx(file);
            }
        } else {
            System.out.println(path + ExamConst.NOT_EXCEL_FILE);
        }
        return null;
    }

    public List<Subject> readXlsx(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Subject subject = null;
        List<Subject> list = new ArrayList<Subject>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    subject = new Subject();
                    subject.setQuestion(getValue(xssfRow.getCell(0)));
                    subject.setAnswerA(getValue(xssfRow.getCell(1)));
                    subject.setAnswerB(getValue(xssfRow.getCell(2)));
                    subject.setAnswerC(getValue(xssfRow.getCell(3)));
                    subject.setAnswerD(getValue(xssfRow.getCell(4)));
                    subject.setAnswer(getValue(xssfRow.getCell(5)));
                    subject.setExplain(getValue(xssfRow.getCell(6)));
                    subject.setSubjectType(subjectTypeMapper.selectByPrimaryKey(Math.round((xssfRow.getCell(7).getNumericCellValue()))));
                    subject.setScore(Double.valueOf(getValue(xssfRow.getCell(8))));
                    subject.setClasses(classesMapper.selectByPrimaryKey(Math.round((xssfRow.getCell(9).getNumericCellValue()))));
                    subject.setUrl(getValue(xssfRow.getCell(10)));
                    subject.setMediaType(getValue(xssfRow.getCell(11)));
                    subject.setProcessStatus(ExamConst.subject_process_status_inactive);
                    subject.setAddtime(new Date());
                    Admin admin = new Admin();
                    admin.setId(1L);
                    subject.setAdduser(admin);
                    list.add(subject);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     *
     * @return
     * @throws IOException
     */
    public List<Subject> readXls(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Subject subject = null;
        List<Subject> list = new ArrayList<Subject>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    subject = new Subject();
                    subject.setQuestion(getValue(hssfRow.getCell(0)));
                    subject.setAnswerA(getValue(hssfRow.getCell(1)));
                    subject.setAnswerB(getValue(hssfRow.getCell(2)));
                    subject.setAnswerC(getValue(hssfRow.getCell(3)));
                    subject.setAnswerD(getValue(hssfRow.getCell(4)));
                    subject.setAnswer(getValue(hssfRow.getCell(5)));
                    subject.setExplain(getValue(hssfRow.getCell(6)));
                    subject.setSubjectType(subjectTypeMapper.selectByPrimaryKey(Math.round((hssfRow.getCell(7).getNumericCellValue()))));
                    subject.setScore(Double.valueOf(getValue(hssfRow.getCell(8))));
                    subject.setClasses(classesMapper.selectByPrimaryKey(Math.round((hssfRow.getCell(9).getNumericCellValue()))));
                    subject.setUrl(getValue(hssfRow.getCell(10)));
                    subject.setMediaType(getValue(hssfRow.getCell(11)));
                    subject.setProcessStatus(ExamConst.subject_process_status_active);
                    list.add(subject);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow == null) {
            return "";
        }
        if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell == null) {
            return "";
        }
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

}
