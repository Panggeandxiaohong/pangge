package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin extends BaseDomain{
    private String name;

    private String username;

    private String password;
}