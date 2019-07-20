package top.yinxiaokang.framework.mybatis.simple.model;

public class UserWithBLOBs extends User {
    private String userInfo;

    private byte[] headImg;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }
}