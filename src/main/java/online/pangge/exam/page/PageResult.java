package online.pangge.exam.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult<T> {
    private Integer total;//总条数
    private List<T> rows;//查询结果集

    public PageResult() {
        super();
    }
    public PageResult(Integer total,List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
}
