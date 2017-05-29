package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class Student extends BaseDomain implements Serializable {
    private String name;

    private String classes;

    private String password;

    private String wechatname;

    private Long stunum;

    private String sex;

    private boolean adminType;
    private Date createTime;
    private Date lastUpdateTime;

//    private List<Role> roles = new ArrayList<>();
}