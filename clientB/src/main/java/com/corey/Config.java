package com.corey;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/14</li>\n
 */
@Configuration
public class Config {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
