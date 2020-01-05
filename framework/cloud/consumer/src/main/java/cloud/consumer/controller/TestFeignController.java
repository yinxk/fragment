package cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.base.response.BaseResponse;
import cloud.base.response.dto.UserInfo;
import cloud.base.response.dto.UserInfo2;
import cloud.base.response.dto.UserInfo3;
import cloud.consumer.feign.TestFeign;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestFeignController {
    @Autowired
    private TestFeign testFeign;
    
    @GetMapping("/feign/info")
    public BaseResponse<UserInfo> info() {
        BaseResponse<UserInfo> info = testFeign.getInfo();
        UserInfo data = info.getData();
        log.info("{}", info.getClass());
        log.info("{}", data.toString());
        return info;
    }
    
    @GetMapping("/feign/info2")
    public BaseResponse<UserInfo2> info2() {
        BaseResponse<UserInfo2> info = testFeign.getInfo2();
        UserInfo2 data = info.getData();
        log.info("{}", info.getClass());
        log.info("{}", data.toString());
        return info;
    }
    
    @GetMapping("/feign/info3")
    public BaseResponse<UserInfo3> info3() {
        BaseResponse<UserInfo3> info = testFeign.getInfo3();
        UserInfo3 data = info.getData();
        log.info("{}", info.getClass());
        log.info("{}", data.toString());
        return info;
    }
    
}
