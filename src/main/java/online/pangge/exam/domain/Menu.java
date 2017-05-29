package online.pangge.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu extends BaseDomain{
    private String text;
    private String iconCls;
    private Boolean checked;
    private String state;
    private String attributes;
    private List<Menu> children;
    //menu解析到前台时,忽略function
    @JsonIgnore
    private String function;
}