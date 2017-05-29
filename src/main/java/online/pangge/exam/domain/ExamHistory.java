package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExamHistory extends BaseDomain {

    private Integer errorCount;

    private Double score;

    private String errorIds;

    private String subIds;

    private Date startTime;

    private Date endTime;

    private Long stuId;
}
