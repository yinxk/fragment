package cloud.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.base.response.BaseResponse;
import cloud.base.response.dto.Car;
import cloud.base.response.dto.DrivingLicense;
import cloud.base.response.dto.UserInfo;

@RestController
public class TestController {

    @GetMapping("/info")
    public BaseResponse<UserInfo> testReturnObject() {
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setIdNumber("testIdNumber");
        drivingLicense.setName("testDrivingName");

        Car car = new Car();
        car.setBrand("testBrand");
        car.setType("testType");
        car.setPrice("100000");
        car.setDrivingLicense(drivingLicense);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("testUserName");
        userInfo.setSex("F");
        userInfo.setCar(car);

        return new BaseResponse<>(userInfo);
    }
}
