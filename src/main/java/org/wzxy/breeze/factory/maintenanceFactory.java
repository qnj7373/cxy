package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.Maintenance;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class maintenanceFactory {



    @Bean
    public Page<MaintenanceDto> createPageMaintenanceDto() {

        return new Page<MaintenanceDto>();

    }

    @Bean
    public Maintenance createMaintenance() {

        return new Maintenance();

    }

    @Bean
    public List<MaintenanceDto> createListMaintenanceDto() {

        return new ArrayList<MaintenanceDto>();

    }

    @Bean
    public List<Maintenance> createListMaintenance () {

        return new ArrayList<Maintenance>();

    }


}
