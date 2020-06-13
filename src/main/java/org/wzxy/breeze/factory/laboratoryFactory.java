package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.po.Laboratory;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class laboratoryFactory {




    @Bean
    public Page<LaboratoryDto> createPageLabDto() {

        return new Page<LaboratoryDto>();

    }

    @Bean
    public Laboratory createLaboratory () {

        return new Laboratory ();

    }

    @Bean
    public LaboratoryDto createLaboratoryDto() {

        return new LaboratoryDto();

    }

    @Bean
    public List<Laboratory> createListLaboratory() {

        return new ArrayList<Laboratory>();

    }

    @Bean
    public List<LaboratoryDto> createListLaboratoryDto() {

        return new ArrayList<LaboratoryDto>();

    }




}
