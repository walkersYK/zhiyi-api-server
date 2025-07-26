package com.yunjin.yunapiinterface;

import com.yunjin.yunapiclientsdk.client.YunApiClient;

import com.yunjin.yunapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 *
 */
@SpringBootTest
class YunapiInterfaceApplicationTests {

    @Resource
    private YunApiClient yunApiClient;

    @Test
    void contextLoads() {
        String result = yunApiClient.getNameByGet("yunjin");
        User user = new User();
        user.setUsername("yunjin");
        String usernameByPost = yunApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
