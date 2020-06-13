package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.WorkRecord;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class workRecordFactory {





    @Bean
    public List<WorkRecord> createListWorkRecord() {

        return new ArrayList<WorkRecord>();

    }

    @Bean
    public  List<WorkRecordDto> createListWorkRecordDto() {

        return new ArrayList<WorkRecordDto>();

    }

    @Bean
    public Page<WorkRecordDto> createPageWorkRecordDto() {

        return new Page<WorkRecordDto>();

    }


    @Bean
    public WorkRecord createWorkRecord() {

        return new WorkRecord();

    }

    @Bean
    public  WorkRecordDto createWorkRecordDto () {

        return new  WorkRecordDto();

    }


}
