package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.Plan;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class planFactory {


    @Bean
    public Page<PlanDto> createPagePlanDto() {

        return new Page<PlanDto>();

    }

    @Bean
    public  Plan  createPlan  () {

        return new  Plan ();

    }

    @Bean
    public PlanDto createPlanDto () {

        return new PlanDto();

    }

    @Bean
    public List<Plan> createListPlan  () {

        return new ArrayList<Plan>();

    }
    @Bean
    public List<PlanDto> createListPlanDto () {

        return new ArrayList<PlanDto>();

    }



}
