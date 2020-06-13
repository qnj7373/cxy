package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Technician;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class techFactory {

    @Bean
    public List<Technician> createListTechnician() {

        return new ArrayList<Technician>();

    }


    @Bean
    public Technician  createTechnician  () {

        return new Technician ();

    }


    @Bean
    public TechnicianDto createTechnicianDto  () {

        return new TechnicianDto();

    }
}
