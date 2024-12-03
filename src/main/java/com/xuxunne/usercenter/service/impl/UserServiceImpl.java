package com.xuxunne.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuxunne.usercenter.domain.User;
import com.xuxunne.usercenter.service.UserService;
import com.xuxunne.usercenter.mapper.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @author JAVA
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-12-03 21:11:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    /*
     * function implement userRegister
     *
     * @date 2024/12/3 22:07
     * @param useAccount
     * @param userPassword
     * @param checkPassWorld
     * @return long
     */
    @Override
    public long userRegister(String useAccount, String userPassword, String checkPassWorld) {
         //1. check
        //  1.2 through apache commons utils: Apache commons method: isAnyBlank to judge multiple string is null
        if (StringUtils.isAllBlank(useAccount,userPassword,checkPassWorld)) {
            return -1;
        }
        //  1.3 to check the account have enough digital
        if (useAccount.length() < 4) {
            return -1;
        }
        //  1.4 check the userPassword have enough digital
        if (userPassword.length() < 8) {
            return -1;
        }

        //  1.5 check the account contain special char
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(useAccount);
        if (matcher.find()){
            return -1;
        }

        //  1.6 verify the password
        if (!userPassword.equals(checkPassWorld)){
            return -1;
        }

        //  1.7 verify the account repeat. put it in the end because it need to pass other verification
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //这行代码的作用是向 queryWrapper 中添加一个条件，表示查询 User 表中 userAccount 字段等于 useAccount 的记录。
        queryWrapper.eq(User::getUserAccount,useAccount);
        long count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;

        }

        //  2. the password to encryption
        final String SALT = "Xuxunnen";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));

        //  insert the user data to the database
        User user = new User();
        user.setUserAccount(useAccount);
        user.setUserPassword(userPassword);
        //  the code is to insert a user data into userTable
        boolean saveResult = this.save(user);
        if (!saveResult){
            return -1;
        }
        return user.getId();

    }
}




