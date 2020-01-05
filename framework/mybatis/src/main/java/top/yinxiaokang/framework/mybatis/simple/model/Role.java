package top.yinxiaokang.framework.mybatis.simple.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Long id;

    private String roleName;

    private Integer enabled;

    private Long createBy;

    private Date createTime;

}