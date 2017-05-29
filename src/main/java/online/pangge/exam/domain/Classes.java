package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Classes extends BaseDomain{
    private String className;

    private String teacher;

    private Date createDate;

    private Date lastUpdateDate;
}