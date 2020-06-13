package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class departmentFactory {

   	//////////////

    @Bean
    public List<Department> createListDepartment  () {

        return new ArrayList<Department>();

    }

    @Bean
    public Page<DepartmentDto> createPageDepartmentDto() {

        return new Page<DepartmentDto>();

    }

    @Bean
    public Department createDepartment () {

        return new Department();

    }

    @Bean
    public DepartmentDto createDepartmentDto () {

        return new DepartmentDto();

    }

    @Bean
    public Page<Department> createPageDepartment () {

        return new Page<Department>();

    }

}
