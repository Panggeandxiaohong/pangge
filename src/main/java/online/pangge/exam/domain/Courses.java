package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Courses {
    private Long id;

    private String name;

    private String teacher;

    private String classesnum;

    private Boolean required;

    private String synopsis;

    private Date createDate;

    private Date lastDupdateDate;
}