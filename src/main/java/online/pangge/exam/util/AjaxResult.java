package online.pangge.exam.util;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AjaxResult {
    private boolean success = false;
    private String msg;

    public  AjaxResult(){}
    public AjaxResult(boolean success,String msg) {
        this.success = success;
        this.msg = msg;
    }
    public AjaxResult(String msg) {
        this.msg = msg;
    }
}
