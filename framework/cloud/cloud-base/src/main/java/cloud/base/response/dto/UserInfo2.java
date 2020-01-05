package cloud.base.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo2 implements Serializable {
    private static final long serialVersionUID = 8622767279291986495L;
    private String name;

    private String sex;

    private String brand;

    private String type;

    private String price;

    private String idNumber;

    private String driName;


    private String testNonPro1;
    private String testNonPro2;
    private String testNonPro3;
    private String testNonPro4;
    private String testNonPro5;
    private String testNonPro6;

}