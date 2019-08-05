package cloud.consumer.feign;

import cloud.base.response.BaseResponse;
import cloud.base.response.dto.UserInfo;
import cloud.base.response.dto.UserInfo2;
import cloud.base.response.dto.UserInfo3;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "provider")
public interface TestFeign {

    @GetMapping("/info")
    BaseResponse<UserInfo> getInfo();

    @GetMapping("/info")
    BaseResponse<UserInfo2> getInfo2();

    @GetMapping("/info")
    BaseResponse<UserInfo3> getInfo3();
}
