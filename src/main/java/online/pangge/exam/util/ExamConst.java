package online.pangge.exam.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie34 on 2017/5/1.
 */
public class ExamConst {
    public static final String redis_key_type_session = "session";
    public static final String redis_key_type_subject = "session";
    public static final String redis_key_type_cache = "session";
    public static final String redis_key_type_permission = "session";
    public static final String redis_key_type_role = "session";


    public static final String redis_key_type_menu = "session";

    public static final String exam_type_exercise = "exercise";
    public static final String exam_type_temp = "temp";
    public static final String exam_type_exam = "exam";
    public static final String exam_type_answer = "answer";

    public static final String wechat_material_type_image = "image";
    public static final String wechat_material_type_text = "text";
    public static final String wechat_material_type_voice = "voice";
    public static final String wechat_material_type_video = "video";
    public static final String wechat_material_type_thumb = "thumb";
    public static final Long wechat_subject_type_choice = 1L;

    public static final Long wechat_subject_type_empty = 2L;
    public static final Long wechat_subject_type_Judgment = 3L;
    public static final String subject_process_status_readyforposting = "ReadyForPosting";

    public static final String subject_process_status_active = "Active";
    public static final String subject_process_status_delete = "Deleted";
    public static final String subject_process_status_Offline = "Offline";
    public static final String subject_process_status_inactive = "Inactive";
    public static List<String> images = new ArrayList<>();

    public static List<String> voices = new ArrayList<>();
    public static List<String> videos = new ArrayList<>();
    public static List<String> subjectsFileType = new ArrayList<>();
    public static List<String> subjectFilesType = new ArrayList<>();


    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "lib";
    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";

    static {
        subjectsFileType.add("xls");
        subjectsFileType.add("xlsx");
        subjectFilesType.add("rar");
        subjectFilesType.add("zip");
        images.add("jpg");
        images.add("jpeg");
        images.add("gif");
        images.add("bmp");
        images.add("png");
        voices.add("mp3");
        voices.add("wma");
        voices.add("wav");
        voices.add("amr");
        videos.add("mp4");
    }
}
