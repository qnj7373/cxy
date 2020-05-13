package org.wzxy.breeze.fatory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.utils.getMyNum;

/**
 * @author 覃能健
 * @create 2020-05
 */
@Configuration
public class fatoryBean {

    @Bean
    public getMyNum MyNum() {
        return  new  getMyNum();
    }

}
