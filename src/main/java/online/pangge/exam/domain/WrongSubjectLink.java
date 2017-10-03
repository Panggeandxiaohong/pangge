package online.pangge.exam.domain;

import lombok.Data;

import java.util.Date;

@Data
public class WrongSubjectLink {
    private Long userId;

    private Long subId;

    private Date lastUpdateDate;
}