package online.pangge.exam.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private Integer page;//当前页
    private Integer rows;//页面大小

    public Integer getStart() {
        return this.page == null ? 0 : (page - 1) * rows;
    }
}
