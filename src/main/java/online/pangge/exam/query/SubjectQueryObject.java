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
    private String subjectType;
}
