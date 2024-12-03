package com.xuxunne.usercenter.service;

import com.xuxunne.usercenter.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author JAVA
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-12-03 21:11:33
*/
public interface UserService extends IService<User> {
    /*
     * function userRegister
     *
     * @date 2024/12/3 22:06
     * @param useAccount
     * @param userPassword
     * @param checkPassWorld
     * @return long
     */
    long userRegister(String useAccount, String userPassword, String checkPassWorld);
}
