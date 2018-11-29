package com.corey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/13</li>\n
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RibbonApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Autowired
    private  RestTemplate restTemplate;

    @RequestMapping("/user")
    String home1 () {
       return  restTemplate.getForObject("http://COREY-PROVIDER-USER/user" ,String.class );
    }


    private static String val = "1";

    public static void main ( String[] args ) {

        SpringApplication.run( RibbonApplication.class, args );
    }
}
