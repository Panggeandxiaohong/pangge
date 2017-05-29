package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jie34 on 2017/4/15.
 */
@Setter
@Getter
abstract public class BaseDomain implements Serializable{
    protected Long id;
}
