package com.bobby.securityjwt.config.mp;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: MybatisPlustConfig
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Configuration
@MapperScan("com.bobby.securityjwt.mapper")
public class MybatisPlusConfig {
    // 分页插件
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
