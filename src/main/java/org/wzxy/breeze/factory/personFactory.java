package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.po.PersonInfo;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class personFactory {
    @Bean
    public PersonInfoDto createPersonInfoDto () {

        return new PersonInfoDto();

    }
    @Bean
    public PersonInfo createPersonInfo  () {

        return new PersonInfo();

    }

    @Bean
    public Page<PersonInfoDto> createPagePersonInfoDto() {

        return new Page<PersonInfoDto>();

    }

    @Bean
    public Page<PersonInfo> createPagePersonInfo  () {

        return new Page<PersonInfo>();

    }


    @Bean
    public List<PersonInfoDto> createListPersonInfoDto() {

        return new ArrayList<PersonInfoDto>();

    }

    @Bean
    public  List<PersonInfo> createListPersonInfo () {

        return new ArrayList<PersonInfo>();

    }



}
