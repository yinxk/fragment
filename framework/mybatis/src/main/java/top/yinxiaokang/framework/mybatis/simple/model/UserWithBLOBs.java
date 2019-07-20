package top.yinxiaokang.framework.mybatis.simple.model;

import lombok.Data;

@Data
public class UserWithBLOBs extends User {
    private String userInfo;

    private byte[] headImg;

}