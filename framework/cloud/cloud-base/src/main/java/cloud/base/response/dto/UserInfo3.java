package cloud.base.response.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo3 implements Serializable {
    private static final long serialVersionUID = 4822032474671928772L;
    private String name;

    private String sex;

    private String carBrand;

    private String carType;

    private String carPrice;

    private String drivingLicenseIdNumber;

    private String drivingLicenseName;
}
