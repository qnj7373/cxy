package org.wzxy.breeze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 覃能健
 * @create 2020-05
 */
@Configuration
public class ResolverConfig implements WebMvcConfigurer {

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10 * 1024 * 1024);// 上传文件大小 5M 5*1024*1024
        return multipartResolver;
    }


}
