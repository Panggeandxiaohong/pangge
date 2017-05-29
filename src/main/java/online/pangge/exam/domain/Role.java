package online.pangge.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Role extends BaseDomain{
    private String sn;
    private String name;
    private List<Permission> permissions = new ArrayList<>();
}