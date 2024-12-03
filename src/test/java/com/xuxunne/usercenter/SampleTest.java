package com.xuxunne.usercenter;

import com.xuxunne.usercenter.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
//
//@SpringBootTest
//public class SampleTest {
//
//    @Resource//默认先按照JavaBean的名称进行注入
//    //   @Autowired 只会按照对象类型去注入
//
//    private UserMapper userMapper;
//
//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
//        userList.forEach(System.out::println);
//    }
//
//}