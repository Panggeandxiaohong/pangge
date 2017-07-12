package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Subject extends BaseDomain{
    //问题
    private String question;
    //类型
    private SubjectType subjectType;
    //分值
    private Double score;
    //班级
    private Classes classes;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String processStatus;
    //标准答案
    private String answer;
    //用户答案
    private String userAnswer;
    //所属用户
    private Student user;
    //解释
    private String explain;
    //视频或图片音频的mediaid
    private String url;
    private Date addtime;
    private Admin adduser;
    //媒体类型，如音频视频图片
    private String mediaType;
    public Subject(){
        super();
    }
}
