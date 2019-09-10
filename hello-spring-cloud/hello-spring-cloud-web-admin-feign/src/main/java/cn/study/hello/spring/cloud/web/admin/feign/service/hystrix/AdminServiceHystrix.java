package cn.study.hello.spring.cloud.web.admin.feign.service.hystrix;

import cn.study.hello.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceHystrix implements AdminService {

    @Override
    public String sayHi(String message) {
        return String.format("Error. Your message is %s", message);
    }
}
