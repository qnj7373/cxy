package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.loginUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class userFactory {


    @Bean
    public List<User> createListUser() {

        return new ArrayList<User>();

    }


    @Bean
    public Page<User> createPageUser() {

        return new Page<User>();

    }


    @Bean
    public User createUser() {

        return new User();

    }


    @Bean
    public loginUser createloginUser() {

        return new loginUser();

    }


    @Bean
    public UserDto createUserDto() {

        return new UserDto();

    }
}
