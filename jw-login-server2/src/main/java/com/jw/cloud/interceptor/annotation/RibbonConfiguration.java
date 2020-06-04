package com.jw.cloud.interceptor.annotation;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: jiawei
 * @Date: 2020/6/4
 * @Description:
 */
@Configuration
@ExcludeFromComponentScan
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
