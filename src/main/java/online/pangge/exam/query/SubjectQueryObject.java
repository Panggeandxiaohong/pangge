package online.pangge.exam.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jie34 on 2017/5/14.
 */
@Getter
@Setter
public class SubjectQueryObject extends QueryObject {
    private String keyword;
    private long subject_type_combobox=-1L;
    private long classes_combobox=-1L;
    private String media_type_combobox;
    private String process_status_combobox;
}
